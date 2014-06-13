class MyDate {
	private static int[] dt;
	static int[] kk;
	/**
	 * 입력된 음력일자에 해당하는 양력일자를 반환한다.
	 *
	 * @param  argDate  변환할 일자( 1998.01.02, 98.01.02, 19980102, 980102 등 )
	 * @param  isLeap   윤달 여부
	 * @return 양력일자
	 */
	public static String toSolarDate(String argDate, boolean isLeap) {
		int syear, smonth, sday = 0;
		int i, j, m1, m2 = 0;
		boolean leap = false;
		long td = 0;

		for(i=0; i<163; i++) {
			dt[i] = 0;
			for(j=0; j<12; j++) {
				switch(kk[i*13 + j]) {
				case 1:
				case 3:
					dt[i] = dt[i] + 29;
					break;
				case 2:
				case 4:
					dt[i] = dt[i] + 30;
				}
			}

			switch(kk[i*13+12]) {
				case 0:
					break;
				case 1:
				case 3:
					dt[i] = dt[i] + 29;
					break;
				case 2:
				case 4:
					dt[i] = dt[i] + 30;
					break;
			}
		}

		String standardDate = toYYYYMMDDDate(argDate);
		int lyear=Integer.parseInt(standardDate.substring(0,4));
		int lmonth=Integer.parseInt(standardDate.substring(4,6));
		int lday=Integer.parseInt(standardDate.substring(6,8));

		boolean isLeapYear = isLeap;

		if (!isLeapYear && !verifyDate(lyear,lmonth,lday,"To Solar-")) {
            throw new IllegalArgumentException("Date Range Error");
		}

		if (isLeapYear && !verifyDate(lyear,lmonth,lday,"To Solar+")) {
            throw new IllegalArgumentException("Date Range or Leap Check Error");
		}

		m1 = -1;
		td = 0L;

		if (lyear != 1881) {
			m1 = lyear - 1882;
			for (i=0; i<=m1; i++) {
				for (j=0; j<13; j++)
					td = td + (long)kk[i*13+j];
				if (kk[i*13+12]==0 )
					td = td + 336L;
				else
					td = td + 362L;
			}
		}

		m1++;
		int n2 = lmonth-1;
		m2 = -1;

		while (true) {
			m2++;
			if ( kk[m1*13+m2] > 2 ) {
				td = td + 26L + (long)kk[m1*13+m2];
				n2++;
			} else if (m2==n2)
				break;
			else
				td = td + 28L + (long)kk[m1*13+m2];
		};

		if ( isLeapYear )
			td = td + 28L + (long)kk[m1*13+m2];

		td = td + (long)lday + 29L;
		m1 = 1880;

		while(true) {
			m1++;
			leap = m1%400==0 || m1%100!=0 && m1%4==0;
			if (leap)
				m2=366;
			else
				m2=365;
			if (td < (long)m2)
				break;
			td = td - (long)m2;
		} ;

		syear = m1;
		dateCount[1] = m2 - 337;
		m1 = 0;
		while(true) {
			m1++;
			if ( td <= (long)dateCount[m1-1] ) break;
			td = td - (long)dateCount[m1-1];
		};

		smonth = m1;
		sday = (int)td;

		long y = syear - 1L;

		td =   (long)(y*365) + (long)(y/4) - (long)(y/100) + (long)(y/400);

		leap = syear%400==0 || syear%100!=0 && syear%4==0;

		if(leap)
			dateCount[1] = 29;
		else
			dateCount[1] = 28;

		for (i=0 ; i<smonth-1 ; i++)
			td = td + (long)dateCount[i];
		td = td + (long)sday;

		i = (int)(td % 10L);
		i = (i+4) % 10;
		j = (int)(td % 12L);
		j = (j+2) % 12;

		Date solarDate = createDate(syear, smonth, sday);

        return dateFormat.format(solarDate);
	}
}