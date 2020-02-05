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

	@Test
	public void testCall() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		callFunction("���� ���� ���� ʮ", map);
		callFunction("���� ���� ��", map);
		callFunction("���� ���� ��", map);
		callFunction("���� ����", map);
		callFunction("��� ���� ���� �� �� ���� ����ã����硱 ���� ���� ���������ˡ�", map);
		//
		callFunction("���� Ǯ�� ���� ��", map);
		callFunction("Ǯ�� ���� ��", map);
		callFunction("Ǯ�� ���� һ", map);
		callFunction("���� Ǯ�� ", map);
		callFunction("���� ���ַ�����", map);
		callFunction("��� Ǯ�� ���� �� �� ���� ��Ǯ̫���ˡ� ���� ���� ���������ˡ�", map);
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
			System.out.println(toChStr(map.get(printStr)));
		}
	}
	@Test
	public void testPrintOut() {
		

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Ǯ��", 10);
		Utils.printOut("���� Ǯ�� ".split(" "), map);
		Utils.printOut("���� ���ַ����� ".split(" "), null);

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
		
		if( judge) {
			callFunction(statement2, map);
		}else {
			callFunction(statement3, map);
		}
	}

	@Test
	public void testTernary() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Ǯ��", 10);
		ternaryOperator("��� Ǯ�� ���� ʮ �� ���� ��Ǯ̫���ˡ� ���� ���� ���������ˡ�", map);

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

	@Test
	public void testJudge() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Ǯ��", 10);
		System.out.println(judgeOperator("�� ���� ��", map));
	}

	/**
	 * ����ת��Ϊ����
	 * @return
	 */
	public static int toNum(String str) {
		return numWords.indexOf(str);// -1������
	}
	
	/**
	 * ����ת��Ϊ����
	 * @param num
	 * @return
	 */
	public static String toChStr(int num) {
		
		return numWords.substring(num,num+1);
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
	

	@Test
	public void testManipulate() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("Ǯ��", 10);
		manipulateNum("Ǯ�� ���� ��", map);
		System.out.println(map.get("Ǯ��"));
	}
}
