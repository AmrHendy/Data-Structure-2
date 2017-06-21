package eg.edu.alexu.csd.filestructure.test.avl;

import eg.edu.alexu.csd.filestructure.avl.*;
import tree.avl.*;
import dictionary.*;

public class TestRunner {

	public static IAVLTree getImplementationInstance() {
		return new AVLTreeImpl();
	}

	public static IDictionary getDicImplementationInstance() {
		return new DictionaryImp();
	}

}
