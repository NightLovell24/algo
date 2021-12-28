package my.nalab2;

public class BTree {

	private Node root;
	private int countOfElements;

	protected int getCountOfElements() {
		return countOfElements;
	}

	protected Node getRoot() {
		return root;
	}

	private int tNode;

	public BTree(int tNode) {
		root = null;
		this.tNode = tNode;
		countOfElements = 0;
	}

	public void traverse() {
		if (root != null) {
			root.traverse();
		}
	}

	public Node search(int key) {
		return root == null ? null : root.search(key);
	}

	public Element searchBiElement(Node node, int key) {

		if (key > countOfElements || key < 0) {
			System.out.println("Ќет такого элемента!");
		}

		Element searchedElement = null;
		Node myNode = searchBiNode(node, key);
		if (myNode == null) {
			return null;
		} else {
//			System.out.println(myNode);
		}

		int index1 = 0;
		int index2 = myNode.keys.length - 1;
		
		for (int i = myNode.keys.length - 1; i >= 0; i--) {
			if (myNode.keys[i] != null) { // ищем граничное значение
				index2 = i;
				
				break;
			}
		}
		

		int tempIndex = (index1 + index2) / 2;

		while (searchedElement == null) {
			if (myNode.keys[tempIndex].key > key) {
				index2 = tempIndex;
				if ((index1 + index2) / 2 != 0) {
					tempIndex = (index1 + index2) / 2;
					tempIndex--;
				}
			}
			if (myNode.keys[tempIndex].key < key) {
				index1 = tempIndex;
				if ((index1 + index2) / 2 != 0) {
					tempIndex = (index1 + index2) / 2;
					tempIndex++;
				}

			}
			if (myNode.keys[tempIndex] != null) {
				if (myNode.keys[tempIndex].key == key) {
					searchedElement = myNode.keys[tempIndex];
				}
			}

		}

		return searchedElement;

	}

	private Node searchBiNode(Node startNode, int key) {
		Node node = null;
		int tempIndex = 0;
		int index1 = 0;
		int index2 = 0;
		int arrayMax = 0;
		for (int i = startNode.keys.length - 1; i >= 0; i--) {
			if (startNode.keys[i] != null) { // ищем граничное значение
				index2 = i;
				arrayMax = i;
				break;
			}
		}
		tempIndex = (tempIndex + index2) / 2;

		if (startNode.keys[tempIndex] == null) {
			return null;
		} else {
			if (startNode.keys[tempIndex].key == key) {
				return startNode;
			} else {
				int indexOfLesser = -1;
				int indexOfBigger = -1;
				while (indexOfBigger == -1 || indexOfLesser == -1) {

					if (tempIndex == 0 || tempIndex == arrayMax) {
						break;
					}

					if ((startNode.keys[tempIndex - 1].key < key && startNode.keys[tempIndex + 1].key > key)) {
						indexOfLesser = tempIndex - 1;
						indexOfBigger = tempIndex + 1;
					} else {

						if (startNode.keys[tempIndex].key < key) {
							index1 = tempIndex;

							if ((index1 + index2) / 2 != 0) {
								tempIndex = (index1 + index2) / 2;
								tempIndex++;
							}
						}
						if (startNode.keys[tempIndex].key > key) {
							index2 = tempIndex;
							
							if ((index1 + index2) / 2 != 0) {
								tempIndex = (index1 + index2) / 2;
								tempIndex--;
							}
						}

					}
				}

				if (startNode.keys[tempIndex].key > key) {
					node = searchBiNode(startNode.descendatns[tempIndex], key);
				}
				if (startNode.keys[tempIndex].key < key) {
					node = searchBiNode(startNode.descendatns[tempIndex + 1], key);
				}
				if (startNode.keys[tempIndex].key == key) {
					return startNode;
				}

			}
		}
		return node;
	}

	public void insert(Element element) {

		if (root == null) {

			root = new Node(tNode, true);
			root.keys[0] = element;
			root.countOfKeys = 1;
			countOfElements++;
		} else {
			//  огда корневой узел заполнитс€, дерево станет выше
			if (root.countOfKeys == 2 * tNode - 1) {
				Node s = new Node(tNode, false);
				// —тарый корневой узел становитс€ дочерним узлом нового корневого узла
				s.descendatns[0] = root;
				// ќтдел€ем старый корневой узел и даем ключ новому узлу
				s.splitChild(0, root);
				// Ќовый корневой узел имеет 2 дочерних узла, переместите туда старый корневой
				// узел
				int i = 0;
				if (s.keys[0].key < element.key)
					i++;
				s.descendatns[i].insertNotFull(element);

				root = s;
//				countOfElements++;
			} else
				root.insertNotFull(element);
			countOfElements++;
		}
	}

	public void remove(Element element) {
		if (root == null) {
			System.out.println("The tree is empty");
			return;
		}

		root.remove(element);

		if (root.countOfKeys == 0) { // ≈сли у корневого узла 0 ключей
			// ≈сли у него есть дочерний узел, используйте его первый дочерний узел как
			// новый корневой узел,
			// ¬ противном случае установите корневой узел в ноль
			if (root.isLeaf)
				root = null;
			else
				root = root.descendatns[0];
		}
	}

}
