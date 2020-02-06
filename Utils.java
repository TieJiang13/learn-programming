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

		if (map.get(keyword) != null) {
			Utils.manipulateNum(str, map);
		} else {

			switch (keyword) {
			case "����":
				// System.out.println(map.get("Ǯ��"));// �����ڷ���null
				int num = Utils.assignInt(split);
				map.put(split[1], num);
				break;
			case "����":
				Utils.printOut(split, map);
				break;
			case "���":
				Utils.ternaryOperator(str, map);
				break;
			default:
				throw new IllegalArgumentException("judgeOperator û�ж�Ӧ���жϷ��������루�����������������: " + keyword);
			}
		}
	}

	/**
	 *  ��ֵ��������
	 * @param array
	 * @return
	 */
	public static int assignInt(String[] array) {
		// �ȿ���һλ���
		return toNum(array[3]);
	}

	/**
	 * ����ַ������߱���
	 * @param array
	 * @param map
	 */
	public static void printOut(String[] array, Map<String, Integer> map) {

		String printStr = array[1];
		if (printStr.contains("��") && printStr.contains("��")) {
			System.out.println(printStr.replace("��", "").replace("��", ""));
		} else {
			try {
				System.out.println(toChStr(map.get(printStr)));
			}catch (NullPointerException e) {
				throw new DemoException("����δ���壬�붨�����");
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
			throw new IllegalArgumentException("judgeOperatorû�ж�Ӧ���жϷ��������루���ڡ����ڡ�С�ڣ�: " + middle);
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
		
		
		if (str.contains("��")) {
			var = 100 * toSingleNum(str.substring(0, str.indexOf('��')));// ��λ
			var = flag * var;
			str = str.substring(str.indexOf('��') + 1);
			if (str.contains("��")) {
				var += toSingleNum(str.substring(str.indexOf("��")+1));// �����㼸
				return var*flag;
			}
		}

		if (str.length() == 1 && var >=100) { //�ж�var ��Ȼʮ ���ܻ����100
			var += 10*toSingleNum(str); //���ټ����Űپ�
			return var*flag;
		}
		
		if (str.contains("ʮ")) {
			if (str.indexOf('ʮ') == 0) {
				var +=10; // ʮ��
			}
			var += 10 * toSingleNum(str.substring(0,str.indexOf('ʮ')));// ʮ
			str = str.substring(str.indexOf('ʮ')+1);
		}
		
		if (str.length() != 0) {
			var += toSingleNum(str);
		}
		
		return var*flag;
	}

	@Test
	public void test2() {

		System.out.println(toNum("һ��һʮһ"));
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
			num = num * -1;
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
		System.out.println(toChStr(-909));
	}

	/**
	 * �������
	 * @param str
	 * @param map
	 */
	public static void manipulateNum(String str, Map<String, Integer> map) {
		String[] strArray = str.trim().split(" ");
		String varStr = strArray[0];
		String operator = strArray[1];
		String numStr = strArray[2];

		int num = 0;
		if (map.get(varStr) != null) {
			num = map.get(varStr);
		} else {
			throw new DemoException("������" + varStr + " δ����");
		}

		switch (operator) {
		case "����":
			num -= toNum(numStr);
			map.put(varStr, num);
			break;
		case "����":
			num += toNum(numStr);
			map.put(varStr, num);
			break;
		default:
			throw new IllegalArgumentException("manipulateNumû�ж�Ӧ���жϷ��������루���ӡ����٣�: " + operator);
		}
	}

}
