import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	static int N; // 정보의 개수
	static String L; // 대여 기간
	static long fine; // 벌금

	static HashMap<String, RentInfo> rentList = new HashMap<>();

	static class RentInfo {
		String rentDate;
		String rentTime;
		String partName;
		String userName;

		RentInfo(String rentDate, String rentTime, String partName, String userName) {
			this.rentDate = rentDate;
			this.rentTime = rentTime;
			this.partName = partName;
			this.userName = userName;
		}
	}

	static class Pair {
		String name;
		long fine;

		Pair(String name, long fine) {
			this.name = name;
			this.fine = fine;
		}
	}

	static int monthToDay(int month) {
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
			return 31;
		else if (month == 4 || month == 6 || month == 9 || month == 11)
			return 30;
		else
			return 28;
	}

	static int calcTime(String date1, String date2, String time1, String time2) {
		int month1 = (date1.charAt(5) - '0') * 10 + (date1.charAt(6) - '0');
		int month2 = (date2.charAt(5) - '0') * 10 + (date2.charAt(6) - '0');

		int day1 = (date1.charAt(8) - '0') * 10 + (date1.charAt(9) - '0');
		int day2 = (date2.charAt(8) - '0') * 10 + (date2.charAt(9) - '0');

		int hour1 = (time1.charAt(0) - '0') * 10 + (time1.charAt(1) - '0');
		int hour2 = (time2.charAt(0) - '0') * 10 + (time2.charAt(1) - '0');

		int minute1 = (time1.charAt(3) - '0') * 10 + (time1.charAt(4) - '0');
		int minute2 = (time2.charAt(3) - '0') * 10 + (time2.charAt(4) - '0');

		if (month1 < month2) {
			for (int i = month1; i < month2; i++)
				day2 += monthToDay(i);
		}

		if (hour1 > hour2) {
			hour2 += 24;
			day2 -= 1;
		}

		if (minute1 > minute2) {
			minute2 += 60;
			hour2 -= 1;
		}
		if (hour1 > hour2) {
			hour2 += 24;
			day2 -= 1;
		}

		int dayDiff = day2 - day1;
		int hourDiff = hour2 - hour1;
		int minuteDiff = minute2 - minute1;

		return dayDiff * 1440 + hourDiff * 60 + minuteDiff;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = st.nextToken();
		fine = Integer.parseInt(st.nextToken());

		int l = ((L.charAt(0) - '0') * 100 + (L.charAt(1) - '0') * 10 + (L.charAt(2) - '0')) * 1440
				+ ((L.charAt(4) - '0') * 10 + (L.charAt(5) - '0')) * 60 + (L.charAt(7) - '0') * 10 + L.charAt(8) - '0';

		TreeMap<String, Long> fineList = new TreeMap<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String date = st.nextToken();
			String time = st.nextToken();
			String part = st.nextToken();
			String user = st.nextToken();

			String key = part + "/" + user;

			if (!rentList.containsKey(key))
				rentList.put(key, new RentInfo(date, time, part, user));
			else {
				RentInfo rent = rentList.get(key);
				rentList.remove(key);

				String rentDate = rent.rentDate;
				String rentTime = rent.rentTime;
				String partName = rent.partName;
				String userName = rent.userName;

				int rentalTime = calcTime(rentDate, date, rentTime, time);
				if (rentalTime > l) {
					if (!fineList.containsKey(userName))
						fineList.put(userName, (long) (rentalTime - l) * fine);
					else
						fineList.put(userName, (long) fineList.get(userName) + (long) (rentalTime - l) * fine);
				}
			}
		}
//		ArrayList<Pair> result = new ArrayList<>();

		if (fineList.isEmpty())
			System.out.println(-1);
		else {
//			while (!fineList.isEmpty()) {
//				fineList.entrySet().stream().findFirst().ifPresent(entry -> {
//					String key = entry.getKey();
//					Integer value = entry.getValue();
//					fineList.remove(key);
//					result.add(new Pair(key, value));
//				});
//			}

			for (Entry<String, Long> entry : fineList.entrySet()) {
				StringBuilder sb = new StringBuilder();
				sb.append(entry.getKey()).append(" ").append(entry.getValue());
				System.out.println(sb);
			}
//			result.sort(Comparator.comparing((Pair p) -> p.name));
//			for (Pair p : result)
//				System.out.println(p.name + " " + p.fine);
		}
	}
}