package Demo1;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class Utils {
	public static String numWords = "��һ�����������߰˾�ʮ";

	/**
	 * ���÷���
	 * @param str
	 * @param map
	 */
	public static void callFunction(String str, Map<String, Integer> map) {
		String[] split = str.trim().split(" ");
		String keyword = split[0];
		if (keyword.equals("��")) {
			return;
		}
		if (isManipulate(str)  || map.get(split[0])!= null) {
			Utils.manipulateNum(str, map);
		} else {

			switch (keyword) {
			case "����":
				// System.out.println(map.get("Ǯ��"));// �����ڷ���null
				int num = Utils.assignInt(str);
				map.put(split[1], num);
				break;
			case "����":
				if (split.length == 1) {
					System.out.println();// ������"����" �������

				} else {
					Utils.printOut(str, map);
				}
				break;
			case "���":
				Utils.ternaryOperator(str, map);
				break;
			default:
				throw new IllegalArgumentException("û�йؼ��֣�" + keyword + " ��ʹ�ùؼ��֣����������������");
			}
		}
	}

	/**
	 *  ��ֵ��������
	 * @param array
	 * @return
	 */
	public static int assignInt(String str) {
		String[] array = str.trim().split(" ");
		return toNum(array[3]);
	}

	/**
	 * ����ַ������߱���
	 * @param array
	 * @param map
	 */
	public static void printOut(String str, Map<String, Integer> map) {
		String[] array = str.trim().split(" ");
		String printStr = array[1];
		if (printStr.contains("��") && printStr.contains("��")) {
			System.out.println(printStr.replace("��", "").replace("��", "")); // ���� ���ַ�����
		} else {
			try {
				System.out.println(toChStr(map.get(printStr)));
			} catch (NullPointerException e) {
				throw new DemoException("������" + printStr + " δ���壬�붨�����");
			}

		}
	}

	/**
	 * ��Ŀ����
	 * @param str
	 * @param map
	 */
	public static void ternaryOperator(String str, Map<String, Integer> map) {
		// ��� Ǯ�� ���� ʮ �� ���� ��Ǯ̫���ˡ� ���� ���� ���������ˡ�
		// �Ȳ�������ĿǶ����Ŀ�����
		String statement1 = str.substring(0, str.indexOf("��")).replace("���", "");
		String statement2 = str.substring(str.indexOf("��"), str.indexOf("����")).replace("��", "");
		String statement3 = str.substring(str.indexOf("����")).replace("����", "");

		boolean judge = judgeOperator(statement1, map);
		// System.out.println(judge);

		if (judge) {
			callFunction(statement2, map);
		} else {
			callFunction(statement3, map);
		}
	}

	/**
	 * �ж���䣬���������� ���磺�� ���� ��
	 * @param str
	 * @param map
	 * @return 
	 */
	public static boolean judgeOperator(String str, Map<String, Integer> map) {

		String[] strArray = str.trim().split(" ");// ��ȥ�����ҿո񣬿ո�ᱻ���뵽�ָ�����
		String leftStr = strArray[0];
		String rightStr = strArray[2];
		String middle = strArray[1];
		int leftInt = 0;
		int rightInt = 0;

		if (map.get(leftStr) != null) {
			leftInt = map.get(leftStr);
		} else {
			leftInt = toNum(leftStr);
		}

		if (map.get(rightStr) != null) {
			rightInt = map.get(rightStr);
		} else {
			rightInt = toNum(rightStr);
		}

		switch (middle) {
		case "����":
			return leftInt == rightInt;
		case "����":
			return leftInt > rightInt;
		case "С��":
			return leftInt < rightInt;

		default:
			throw new IllegalArgumentException("û�йؼ��֣�"+ middle + " ��ʹ�ùؼ��֣����ڡ����ڡ�С��");
		}
	}

	/**
	 * ���ֵ���ת��Ϊ��������
	 * @param str
	 * @return
	 */

	public static int toSingleNum(String str) {
		return numWords.indexOf(str);// -1������
	}

	/**
	 * ��������ת��Ϊ��������
	 * @param num
	 * @return
	 */
	public static String toSingleChStr(int num) {

		return numWords.substring(num, num + 1);
	}

	/**
	 * ����ת��Ϊ����
	 * @return
	 */
	public static int toNum(String str) {
		int flag = 1;// �������
		int var = 0;
		char[] arr = str.toCharArray();
		if (arr[0] == '��') {
			str = str.replace("��", "");

			flag = -1;
		}
		// ���ֻ�еʽ��ת��
		if (str.length() > 1 && !str.contains("��") && !str.contains("ʮ")) {
			char[] chArr = str.toCharArray();
			int temp = 1;
			for (int i = chArr.length - 1; i >= 0; i--) {
				String s = "" + chArr[i];
				var += temp * toSingleNum(s);
				temp *= 10;
			}
			return var * flag;
		}

		if (str.contains("��")) {
			var = 100 * toSingleNum(str.substring(0, str.indexOf('��')));// ��λ
			str = str.substring(str.indexOf('��') + 1);
			if (str.contains("��")) {
				var += toSingleNum(str.substring(str.indexOf("��") + 1));// �����㼸
				return var * flag;
			}
		}

		if (str.length() == 1 && var >= 100) { // �ж�var ��Ȼʮ ���ܻ����100
			var += 10 * toSingleNum(str); // ���ټ����Űپ�
			return var * flag;
		}

		if (str.contains("ʮ")) {
			if (str.indexOf('ʮ') == 0) {
				var += 10; // ʮ��
			}
			var += 10 * toSingleNum(str.substring(0, str.indexOf('ʮ')));// ʮ
			str = str.substring(str.indexOf('ʮ') + 1);
		}

		if (str.length() != 0) {
			var += toSingleNum(str);
		}

		return var * flag;
	}

	@Test
	public void test2() {
		// Java int Max ��2147483647 ��һ�����İ��������� Min��-2147483648
		// System.out.println(toNum("����һ�����İ������İ�"));
		System.out.println(toNum("���žž�"));
	}

	/**
	 * ����ת��Ϊ����
	 * @param num
	 * @return
	 */
	public static String toChStr(int num) {

		String varStr = "";

		if (num < 0) {
			varStr += "��";
			num = num * -1; // ת��Ϊ����
		}

		// ���ֻ�еʽ��ת��
		if (num > 999) {
			// 1234
			char[] CharArr = Integer.toString(num).toCharArray();
			for (char c : CharArr) {
				String s = "" + c;
				varStr += toSingleChStr(Integer.parseInt(s));
			}
			return varStr;
		}
		if (num == 0) { // ��
			return "��";
		}

		if (num / 10 == 1) { // ��ʮ�� �ı�һʮ����д��
			varStr += "ʮ";
			if (num % 10 != 0) {
				varStr += toSingleChStr(num % 10);
			}
			return varStr;
		}

		if (num / 100 > 0) {
			varStr += toSingleChStr(num / 100) + "��";
			if (num % 100 == 0) {// ����
				return varStr;
			} else if (num % 100 < 10) { // �����㼸
				return varStr += "��" + toSingleChStr(num % 100);
			}
		}

		num %= 100;
		if (num / 10 != 0) {
			varStr += toSingleChStr(num / 10) + "ʮ";// ʮ
		}

		if (num % 10 != 0) {
			varStr += toSingleChStr(num % 10); // ��
		}

		return varStr;
	}

	@Test
	public void test() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Ǯ��", 10);
		System.out.println(toChStr(9999));
	}

	/**
	 * �������
	 * @param str
	 * @param map
	 */
	public static void manipulateNum(String str, Map<String, Integer> map) {
		String[] strArray = str.trim().split(" ");
		String operator = strArray[1];
		
		if (strArray.length == 2) {
			throw new DemoException("ȱ�� "+operator+" �Ĳ���");
		}
		
		String varStr = strArray[0];
		String numStr = strArray[2];

		int var = 0;
		int num = toNum(numStr);

		if (map.get(varStr) != null) {
			var = map.get(varStr);
		} else {
			throw new DemoException("������" + varStr + " δ���壬�붨�����");
		}

		switch (operator) {
		case "����":
			var -= num;
			break;
		case "����":
			var += num;
			break;
		case "����":
			var *= num;
			break;
		case "����":
			if (num == 0) {
				throw new DemoException("�������ܵ�����Ŷ");
			} else {
				var /= num;
			}
			break;
		case "ģ��":
			var %= num;
			break;
		default:
			throw new IllegalArgumentException("û�йؼ��֣�"+ operator + " ��ʹ�ùؼ��֣����ӡ����١����ԡ����ԡ�ģ�� " );
		}

		map.put(varStr, var);
	}

	@Test
	public void testManipulate() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Ǯ��", -3);
		manipulateNum("Ǯ�� ģ�� ʮ", map);
		printOut("���� Ǯ��", map);
	}

	/**
	 * �ж��Ƿ�Ϊ���������䣨��
	 * @param str
	 * @return
	 */
	public static boolean isManipulate(String str) {
		String[] array = str.trim().split(" ");

		String[] keywords = { "����", "����", "����", "����", "ģ��" };
		if (array.length==1) {
			throw new DemoException("�﷨�д������﷨");
		}
		String symbol = array[1].trim();

		for (String s : keywords) {
			if (symbol.equals(s)) {
				return true;
			}
		}
		return false;
	}

	@Test
	public void testSymbol() {
		System.out.println(isManipulate("Ǯ�� ģ��  ʮ"));
	}
}
