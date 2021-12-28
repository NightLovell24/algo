package my.nalab2;

public class Main {

	public static void main(String[] args) {
		BTree bTree = new BTree(25);

		String source = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < 33333; i++) {

			for (int j = 0; j < 6; j++) {
				int index = (int) (Math.random() * source.toCharArray().length);
				builder.append(source.toCharArray()[index]);
			}
			bTree.insert(new Element(i, builder.toString()));
			builder.setLength(0);

		}

//		bTree.traverse();
//System.out.println(bTree.getCountOfElements());
		Element element = bTree.searchBiElement(bTree.getRoot(), 1666);
		if (element == null)
		{
			System.out.println("ya loh");
		}
		else
		{
			System.out.println(element);
		}
	

	}

}
