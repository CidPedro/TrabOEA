import java.io.RandomAccessFile;
import java.util.Random;

public class Principal {

	public static void main(String args[]) throws Exception
	{
		// Arquivos que serao utilizados e/ou alterados
		RandomAccessFile a = new RandomAccessFile("cep_ordenado.dat", "r");
		RandomAccessFile b = new RandomAccessFile("cep1.dat", "rw");
    	RandomAccessFile c = new RandomAccessFile("cep2.dat", "rw");
    	RandomAccessFile d = new RandomAccessFile("ordenadofinal.dat","rw");
		Endereco e = new Endereco();
		// Numero aleatorio entre 1 e 50
		Random rand = new Random();
		int  r = rand.nextInt(50) + 1;
		// Cria 2 arquivos aleatorios com as linhas do cep_ordenado
		for(int i=0; i<a.length()/300;i++) {
			if(r <= 25) {
				a.seek(i*300);
				e.leEndereco(a);
				e.escreveEndereco(b);
				r = rand.nextInt(50) + 1;
			}else {
				a.seek(i*300);
				e.leEndereco(a);
				e.escreveEndereco(c);
				r = rand.nextInt(50) + 1;
			}
		}
		
		// Checa os dois arquivos criados e junta em um igual ao cep_ordenado
		
		Endereco f = new Endereco();
		int k = 0;
		int j = 0;
		b.seek(0);
		e.leEndereco(b);
		c.seek(0);
		f.leEndereco(c);
		
		boolean check1 = false; 
		boolean check2 = false;

			for(int i=0; i<a.length()/300;i++) {
				if (Integer.parseInt(e.cep) < Integer.parseInt(f.cep)) {
					e.escreveEndereco(d);
					
					if (check1 == false) {
						j++;
						b.seek(j*300);
						e.leEndereco(b);
						
						if ((j+1) >= b.length()/300)
							check1 = true;
					}
					else
						break;
				}
				else {
					f.escreveEndereco(d);
					
					if (check2 == false) {
						k++;
						c.seek(k*300);
						f.leEndereco(c);
						
						if ((k+1) >= c.length()/300)
							check2 = true;
					}
					else
						break;
				}
			}

			if (check2 == true) {
				for (int x = j;j<b.length()/300;j++) {
					b.seek(j*300);
					e.leEndereco(b);
					e.escreveEndereco(d);
				}
			}
			else if (check1 == true) {
				for (int y = k;k<c.length()/300;k++) {
					c.seek(k*300);
					f.leEndereco(c);
					f.escreveEndereco(d);
				}
			}

		a.close();
		b.close();
	    c.close();
	    d.close();
	    System.out.println("Fim");
	}
}
