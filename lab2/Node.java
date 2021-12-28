package my.nalab2;

public class Node {

	Element[] keys;
	int tNode;
	Node[] descendatns;
	int countOfKeys;
	boolean isLeaf;

	public Node(int tNode, boolean isLeaf) {
		this.tNode = tNode;
		this.isLeaf = isLeaf;
		keys = new Element[2 * this.tNode - 1];
		descendatns = new Node[2 * tNode];
		countOfKeys = 0;
	}

	public Element findKey(int key) {

		int tempIndex = 0; // темп индекс
		while (tempIndex < countOfKeys && keys[tempIndex].key < key) {
			tempIndex++; // ищем ключ равный или больше нашего; самое правое
		}
		return keys[tempIndex];

	}

	public void remove(Element element) {
		int tempIndex = findKey(element.key).key;
		if (tempIndex < countOfKeys && keys[tempIndex] == element) { // Если ключ найден в этом узле
			if (isLeaf) {
				removeFromLeaf(tempIndex);
			} else {
				removeFromNonLeaf(tempIndex);
			}
		} else {
			if (isLeaf) { // Если текущая нода является листовой, то ключа не существует
				System.out.printf("Нема ключа такого в этом дереве");
				return;
			}

			boolean flag = tempIndex == countOfKeys; // существует ли ключ в потомках

			if (descendatns[tempIndex].countOfKeys < tNode) // если самый правый наследник не соответствует нашему B
			// дереву
			{
				fill(tempIndex);
			}

			if (flag && tempIndex > countOfKeys) {
				descendatns[tempIndex - 1].remove(element);
			} else {
				descendatns[tempIndex].remove(element);
			}
		}
	}

	public void removeFromLeaf(int tempIndex) {

		// возвращаемся из tempIndex
		for (int i = tempIndex + 1; i < countOfKeys; ++i)
			keys[i - 1] = keys[i];
		countOfKeys--;
	}

	public void removeFromNonLeaf(int tempIndex) {

		Element key = keys[tempIndex];

		// Если у левого брата можно взять ключ, то перемещаем самый последний
		if (descendatns[tempIndex].countOfKeys >= tNode) {
			Element pred = getPred(tempIndex);
			keys[tempIndex] = pred;
			descendatns[tempIndex].remove(pred);
		}
		// Если у правого брата можно взять ключ, то перемещаем самый первый
		else if (descendatns[tempIndex + 1].countOfKeys >= tNode) {
			Element succ = getSucc(tempIndex);
			keys[tempIndex] = succ;
			descendatns[tempIndex + 1].remove(succ);
		} else {
			// Если у обоих братьев нельзя взять ключ, то просто обьединяем их
			merge(tempIndex);
			descendatns[tempIndex].remove(key);
		}
	}

	public Element getPred(int tempIndex) {
		Node cur = descendatns[tempIndex];
		while (!cur.isLeaf)
			cur = cur.descendatns[cur.countOfKeys];
		return cur.keys[cur.countOfKeys - 1];
	}

	public Element getSucc(int tempIndex) {
		Node cur = descendatns[tempIndex + 1];
		while (!cur.isLeaf)
			cur = cur.descendatns[0];
		return cur.keys[0];
	}

	// Вытаскиваем ключики с братьев
	public void fill(int tempIndex) {

		// Если левый братуха может дать ключ и соответсвуем условии би-дерева
		if (tempIndex != 0 && descendatns[tempIndex - 1].countOfKeys >= tNode)
			borrowFromPrev(tempIndex);
		// Если левый подвел, а правый может, то возьмем с правого
		else if (tempIndex != countOfKeys && descendatns[tempIndex + 1].countOfKeys >= tNode)
			borrowFromNext(tempIndex);
		else {
			// если оба братья подвели, то возьмем с 2х сразу. Скриншоты есть
			if (tempIndex != countOfKeys)
				merge(tempIndex);
			else
				merge(tempIndex - 1);
		}
	}

	public void borrowFromPrev(int tempIndex) {

		Node child = descendatns[tempIndex];
		Node brotha = descendatns[tempIndex - 1]; // левый кент

		// последний ключ перейдет в родительскую хрень (Скрины)
		for (int i = child.countOfKeys - 1; i >= 0; --i) {
			// смещаем наши ключики влево

			child.keys[i + 1] = child.keys[i];
		}

		if (!child.isLeaf) {
			for (int i = child.countOfKeys; i >= 0; --i) {
				child.descendatns[i + 1] = child.descendatns[i]; // смешаем ноды нашего потомка влево
			}
		}

		// Устанавливаем первый ключ дочернего узла на ключи текущего узла [tempIndex-1]
		child.keys[0] = keys[tempIndex - 1];
		if (!child.isLeaf) // Устанавливаем последний дочерний узел в качестве первого дочернего узла
							// дочерних элементов [tempIndex]
			child.descendatns[0] = brotha.descendatns[brotha.countOfKeys];

		// Перемещаем последний ключ брата к последнему из текущего узла
		keys[tempIndex - 1] = brotha.keys[brotha.countOfKeys - 1];
		child.countOfKeys += 1;
		brotha.countOfKeys -= 1;
	}

	// симметрично Prev
	public void borrowFromNext(int tempIndex) {

		Node child = descendatns[tempIndex];
		Node sibling = descendatns[tempIndex + 1];

		child.keys[child.countOfKeys] = keys[tempIndex];

		if (!child.isLeaf)
			child.descendatns[child.countOfKeys + 1] = sibling.descendatns[0];

		keys[tempIndex] = sibling.keys[0];

		for (int i = 1; i < sibling.countOfKeys; ++i)
			sibling.keys[i - 1] = sibling.keys[i];

		if (!sibling.isLeaf) {
			for (int i = 1; i <= sibling.countOfKeys; ++i)
				sibling.descendatns[i - 1] = sibling.descendatns[i];
		}
		child.countOfKeys += 1;
		sibling.countOfKeys -= 1;
	}

	// обьединить левого и правого братишки
	public void merge(int tempIndex) {

		Node child = descendatns[tempIndex];
		Node sibling = descendatns[tempIndex + 1];

		// Вставляем последний ключ текущего узла в позицию tNode-1 дочернего узла
		child.keys[tNode - 1] = keys[tempIndex];

		// ключи: descendatns [tempIndex + 1] скопированы в descendatns [tempIndex]
		for (int i = 0; i < sibling.countOfKeys; ++i)
			child.keys[i + tNode] = sibling.keys[i];

		// descendatns: descendatns [tempIndex + 1] скопированы в descendatns
		// [tempIndex]
		if (!child.isLeaf) {
			for (int i = 0; i <= sibling.countOfKeys; ++i)
				child.descendatns[i + tNode] = sibling.descendatns[i];
		}

		// Перемещаем клавиши вперед, а не зазор, вызванный перемещением ключей
		// [tempIndex] к
		// дочерним [tempIndex]
		for (int i = tempIndex + 1; i < countOfKeys; ++i)
			keys[i - 1] = keys[i];
		// Перемещаем соответствующий дочерний узел вперед
		for (int i = tempIndex + 2; i <= countOfKeys; ++i)
			descendatns[i - 1] = descendatns[i];

		child.countOfKeys += sibling.countOfKeys + 1;
		countOfKeys--;
	}

	public void insertNotFull(Element element) {

		int i = countOfKeys - 1; // Самый правый индекс

		if (isLeaf) { // Когда это листовой узел
			// Находим, куда нужно вставить новый ключ
			while (i >= 0 && keys[i].key > element.key) {
				keys[i + 1] = keys[i]; // смещение влево
				i--;
			}
			keys[i + 1] = element;
			countOfKeys++;
		} else {

			while (i >= 0 && keys[i].key > element.key)
				i--;
			if (descendatns[i + 1].countOfKeys == 2 * tNode - 1) {
				splitChild(i + 1, descendatns[i + 1]);

				if (keys[i + 1].key < element.key)
					i++;
			}
			descendatns[i + 1].insertNotFull(element);
		}
	}

	public void splitChild(int i, Node y) {

		// Сначала создаем узел, содержащий ключи tNode-1 y
		Node z = new Node(y.tNode, y.isLeaf);
		z.countOfKeys = tNode - 1;

		// Передаем все атрибуты y в z
		for (int j = 0; j < tNode - 1; j++)
			z.keys[j] = y.keys[j + tNode];
		if (!y.isLeaf) {
			for (int j = 0; j < tNode; j++)
				z.descendatns[j] = y.descendatns[j + tNode];
		}
		y.countOfKeys = tNode - 1;

		// Вставляем новый дочерний узел в дочерний узел
		for (int j = countOfKeys; j >= i + 1; j--)
			descendatns[j + 1] = descendatns[j];
		descendatns[i + 1] = z;

		// Перемещаем ключ по y к этому узлу
		for (int j = countOfKeys - 1; j >= i; j--)
			keys[j + 1] = keys[j];
		keys[i] = y.keys[tNode - 1];

		countOfKeys = countOfKeys + 1;
	}

	public void traverse() {
		int i;
		for (i = 0; i < countOfKeys; i++) {
			if (!isLeaf)
				descendatns[i].traverse();
			System.out.print(keys[i].toString() + " ");
		}
		System.out.println();

		if (!isLeaf) {
			descendatns[i].traverse();
		}
	}

	public Node search(int key) {
		int i = 0;
		while (i < countOfKeys && key > keys[i].key)
			i++;

		if (keys[i].key == key)
			return this;
		if (isLeaf)
			return null;
		return descendatns[i].search(key);
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Element element : keys)
		{
			if (element != null)
			{
			builder.append(element.key + " ");
			}
		}
		return "Наш узел :" + builder;
	}

}
