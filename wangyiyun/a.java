public class a{
	public static void main(String[] args){
		int x = 500;
		int y = 100;
		int a = x/y;
		int b = 50;
		System.out.print(a+b);
	}
}

sudo docker run -p 4406:3306 --name mysql -e MYSQL_ROOT_PASSWORD=123456 -d mysql

int findYzCount(BTree t){
	if(t == null){
		return 0;
	}
	if(t->left==null && t->right == null){
		return 1;
	}else{
		return findYzCount(t->left)+findYzCount(t->right);
	}
}