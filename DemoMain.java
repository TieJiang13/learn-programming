package Demo1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DemoMain {

	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<String, Integer>();

		Scanner sc = new Scanner(System.in);
		while (true) {
			Utils.callFunction(sc.nextLine().trim(), map);
		}
	}

	public void line() {

		String lines2 = "���� Ǯ�� ���� ��\r\n" + "Ǯ�� ���� ��\r\n" + "Ǯ�� ���� ��\r\n" + "���� Ǯ�� or ���� ���ַ�����\r\n"
				+ "��� Ǯ�� ���� ʮ �� ���� ��Ǯ̫���ˡ� ���� ���� ���������ˡ�";
		String lines3 = "���� ���� ���� ʮ\r\n" + "���� ���� ��\r\n" + "���� ���� ��\r\n" + "���� ����\r\n"
				+ "��� ���� ���� �� �� ���� ����ã����硱 ���� ���� ���������ˡ�";
		/*
		String lines = sc.nextLine();
		String[] strArray = lines.split("\n");

		for (String str : strArray) {
			// System.out.println(str.trim());
			// Utils.callFunction(str.trim(), map);
		}
		*/
	}

}
