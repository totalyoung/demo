package ja.dataStrcutures.avl;

public class AvlNode {

    int element;
    AvlNode left;
    AvlNode right;
    int height;

    public AvlNode(int element, AvlNode left, AvlNode right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }

    public AvlNode(int element) {
        this(element, null, null);
    }

    int height(AvlNode t) {
        return t == null ? -1 : t.height;
    }

    static final int allowed = 1;

    /**
     *
     * @param x 所插入元素
     * @param t 子树的根节点
     * @return 子树的根节点
     */
    AvlNode insert(int x,AvlNode t){
        if(t==null)
            return new AvlNode(x,null,null);
        int a = x-t.element;
        if(a<0){
            t.left = insert(x,t.left);
        }else if(a>0){
            t.right = insert(x,t.right);
        }else{

        }
        return balance(t);
    }

    AvlNode balance(AvlNode t){
        if(t==null)
            return t;
        if(height(t.left)-height(t.right)>allowed){
            if (height(t.left.left)>=height(t.left.right)){

            }else{

            }
        }else if(height(t.right)-height(t.left)>allowed) {
            if (height(t.right.right) >= height(t.right.left)) {

            } else {

            }
        }
        t.height = Math.max(height(t.left),height(t.right))+1;
        return t;
    }

    AvlNode rotateWithLeftChild(AvlNode k2){
        AvlNode k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left),height(k2.right))+1;
        k1.height = Math.max(height(k1.left),k2.height)+1;
        return k1;
    }




}
