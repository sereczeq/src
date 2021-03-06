package lab8;

public class Reader {
	// TODO: change that for ||

	private static int binaryStringToNumber(String binary) {

		char[] charFromNumber = binary.trim().toCharArray();

		if (charFromNumber.length > 34) {
			return 0;
		}

		char[] charNumber = new char[32];
		int where = 31;

		for (int x = charFromNumber.length - 1; x >= 0; x--) {
			if (charFromNumber[x] == '\"')
				continue;
			charNumber[where] = charFromNumber[x];
			where--;
		}
		for (int x = 0; x < charNumber.length; x++) {
			if (charNumber[x] != '1' && charNumber[x] != '0')
				charNumber[x] = '0';
		}

		return new Binary(charNumber).getInteger();

	}

	private static int integerStringToNumber(String number) {

		try {
			return Integer.parseInt(number.trim());
		} catch (Exception ex) {
			return 0;
		}

	}

	private static int[] stringToNumbers(String[] numbers) {

		int[] array = new int[2];

		for (int x = 0; x < 2; x++) {
			if (numbers[x].contains("\""))
				array[x] = binaryStringToNumber(numbers[x]);
			else
				array[x] = integerStringToNumber(numbers[x]);
		}

		return array;

	}

	public static Binary calculator(String line) {

		line = line.trim();

		String exp = "&&";
		if (line.contains(exp)) {
			int[] array = stringToNumbers(line.split(exp));
			if (array != null)
				return new Binary(array[0]).calcAND(new Binary(array[1]));
		}

		exp = "||";
		if (line.contains(exp) && !line.contains("xor")) {
			int[] array = stringToNumbers(line.split("\\|\\|"));
			if (array != null)
				return new Binary(array[0]).calcOR(new Binary(array[1]));
		}

		exp = "^";
		if (line.contains(exp)) {
			int[] array = stringToNumbers(line.split("\\^"));
			if (array != null)
				return new Binary(array[0]).calcXOR(new Binary(array[1]));
		}

		return new Binary(0);

	}

	public static void main(String[] args) {
		String line = "5 && 8";
		Reader r = new Reader();
		System.out.println(calculator(line).getInteger());
		line = "7 || \"1\"";
		System.out.println(calculator(line));
		line = "\"001\" ^ \"11\"";
		System.out.println(calculator(line));
	}

}
