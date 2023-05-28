package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import br.juliarafaela.ListaInt.Lista;
import model.Grupos;

public class ConsultaCorntoller implements ActionListener {
	 private JTextField  textFieldCodgpCp;
	 private  JTextArea  textAreaCG;
	 private  JTextField textFieldNOMEGP;
		private  JTextField textFieldTEMAGP;
		private  JTextField textFieldAREA;
		private  JTextField textFieldSUBAREA;
		private  JTextField textFieldDATA;
		private  JTextArea  TextAreaNomeAlunosGP;
		private  JTextArea  textAreaRAGP;

	

	public ConsultaCorntoller(JTextField textFieldCodgpCp, JTextArea textAreaCG, JTextField textFieldNOMEGP,
				JTextField textFieldTEMAGP, JTextField textFieldAREA, JTextField textFieldSUBAREA,
				JTextField textFieldDATA, JTextArea textAreaNomeAlunosGP,
				JTextArea textAreaRAGP) {
			super();
			this.textFieldCodgpCp = textFieldCodgpCp;
			this.textAreaCG = textAreaCG;
			this.textFieldNOMEGP = textFieldNOMEGP;
			this.textFieldTEMAGP = textFieldTEMAGP;
			this.textFieldAREA = textFieldAREA;
			this.textFieldSUBAREA = textFieldSUBAREA;
			this.textFieldDATA = textFieldDATA;
			TextAreaNomeAlunosGP = textAreaNomeAlunosGP;
			this.textAreaRAGP = textAreaRAGP;
		}



	@Override
	public void actionPerformed(ActionEvent e) {
		String cdm = e.getActionCommand();
		if(cdm.equals("Pesquisar")) {
			
				try {
					Consulta();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	}
		
	}



	private void Consulta() throws Exception {
		Grupos grupo=new Grupos();
		grupo.codigo=textFieldCodgpCp.getText();
		grupo.nome=textFieldNOMEGP.getText();
		grupo.tema=textFieldTEMAGP.getText();
		grupo.area=textFieldAREA.getText();
		grupo.subarea=textFieldSUBAREA.getText();
		grupo.data=textFieldDATA.getText();
		grupo.alunos=TextAreaNomeAlunosGP.getText();
		grupo.Ra=textAreaRAGP.getText();
		
			Lista ConGP = new Lista();
			if(grupo.codigo.equals("")){
				
			grupo = buscaGrupo(grupo.codigo);
		if(grupo.codigo!= null) {
			textAreaCG.setText("Codigo: "+ grupo.codigo+" - nome: "+grupo.nome +"- tema "+ grupo.tema+ "- area "+ grupo.area+ "- subarea"+ grupo.subarea+ "-data "+ grupo.data+ "- alunos "+ grupo.alunos+ "- Ra "+ grupo.Ra);
		} 
			}else if(!grupo.nome.equals("")) {
			ConGP = buscaNome(grupo.nome);
		}else if(!grupo.tema.equals("")) {
			ConGP = buscaTema(grupo.tema);
		}else if(!grupo.area.equals("")) {
			ConGP = buscaArea(grupo.area);
		}else if(!grupo.subarea.equals("")) {
			ConGP = buscaSubarea(grupo.subarea);
		}else if(!grupo.data.equals("")) {
			ConGP = buscaData(grupo.data);
		}else if(!grupo.alunos.equals("")) {
			ConGP = buscaAlunos(grupo.alunos);
		}else if(!grupo.Ra.equals("")) {
			ConGP = buscaRa(grupo.Ra);
		} else {
			JOptionPane.showMessageDialog(null, "Prencha um campo", "ERRO!", JOptionPane.ERROR_MESSAGE);
		}
			int tamanholista = ConGP.size();
			StringBuffer buffer = new StringBuffer();
			for(int i=0; i<tamanholista;i++) {
				Grupos g =(Grupos) ConGP.get(i);
				buffer.append("Codigo: "+ g.codigo+" - nome: "+g.nome +"- tema "+g.tema+ "- area "+ g.area+ "- subarea"+ g.subarea+ "-data "+ g.data+ "- alunos "+ g.alunos+ "- Ra "+ g.Ra);
			}
			textAreaRAGP.setText(buffer.toString());
			}
	


	private Lista buscaNome(String nome) throws IOException {
		Lista ConGP = new Lista();
		 String path= System.getProperty ("user.home") + File.separator + "SistemaCadastro";
		 File arq= new File(path, "grupos.csv");
		 if(arq.exists() && arq.isFile()) {
			 FileInputStream fis=new FileInputStream(arq);
			 InputStreamReader isr= new InputStreamReader(fis);
			 BufferedReader buffer= new BufferedReader(isr);
			 String linha=buffer.readLine();
			 while(linha!=null) {
				 String[] vetLinha=linha.split(";");
				 if(vetLinha[0].equals(nome)) {
					 Grupos grupo=new Grupos();
					 grupo.nome=vetLinha[0];
					 grupo.tema=vetLinha[1];
					 grupo.area=vetLinha[2];
					 grupo.subarea=vetLinha[3];
					 grupo.data=vetLinha[4];
					 grupo.codigo=vetLinha[5];
					 grupo.alunos=vetLinha[6];
					 grupo.Ra=vetLinha[7];
					 
					 ConGP.addFirst(grupo);
				 }
				 linha=buffer.readLine();
			 }
			 buffer.close();
			 isr.close();
			 fis.close();
		 }
	
		 return ConGP;
		
	}



	private Lista buscaTema(String tema) throws IOException {
		Lista ConGP = new Lista();
		 String path= System.getProperty ("user.home") + File.separator + "SistemaCadastro";
		 File arq= new File(path, "grupos.csv");
		 if(arq.exists() && arq.isFile()) {
			 FileInputStream fis=new FileInputStream(arq);
			 InputStreamReader isr= new InputStreamReader(fis);
			 BufferedReader buffer= new BufferedReader(isr);
			 String linha=buffer.readLine();
			 while(linha!=null) {
				 String[] vetLinha=linha.split(";");
				 if(vetLinha[1].equals(tema)) {
					 Grupos grupo=new Grupos();
					 grupo.nome=vetLinha[0];
					 grupo.tema=vetLinha[1];
					 grupo.area=vetLinha[2];
					 grupo.subarea=vetLinha[3];
					 grupo.data=vetLinha[4];
					 grupo.codigo=vetLinha[5];
					 grupo.alunos=vetLinha[6];
					 grupo.Ra=vetLinha[7];
					 
					 ConGP.addFirst(grupo);
				 }
				 linha=buffer.readLine();
			 }
			 buffer.close();
			 isr.close();
			 fis.close();
		 }
	
		 return ConGP;
		
	}



	private Lista buscaRa(String ra) throws IOException {
		Lista ConGP = new Lista();
		 String path= System.getProperty ("user.home") + File.separator + "SistemaCadastro";
		 File arq= new File(path, "grupos.csv");
		 if(arq.exists() && arq.isFile()) {
			 FileInputStream fis=new FileInputStream(arq);
			 InputStreamReader isr= new InputStreamReader(fis);
			 BufferedReader buffer= new BufferedReader(isr);
			 String linha=buffer.readLine();
			 while(linha!=null) {
				 String[] vetLinha=linha.split(";");
				 if(vetLinha[7].equals(ra)) {
					 Grupos grupo=new Grupos();
					 grupo.nome=vetLinha[0];
					 grupo.tema=vetLinha[1];
					 grupo.area=vetLinha[2];
					 grupo.subarea=vetLinha[3];
					 grupo.data=vetLinha[4];
					 grupo.codigo=vetLinha[5];
					 grupo.alunos=vetLinha[6];
					 grupo.Ra=vetLinha[7];
					 
					 ConGP.addFirst(grupo);
				 }
				 linha=buffer.readLine();
			 }
			 buffer.close();
			 isr.close();
			 fis.close();
		 }
	
		 return ConGP;
		
	}



	private Lista buscaAlunos(String alunos) throws IOException {
		Lista ConGP = new Lista();
		 String path= System.getProperty ("user.home") + File.separator + "SistemaCadastro";
		 File arq= new File(path, "grupos.csv");
		 if(arq.exists() && arq.isFile()) {
			 FileInputStream fis=new FileInputStream(arq);
			 InputStreamReader isr= new InputStreamReader(fis);
			 BufferedReader buffer= new BufferedReader(isr);
			 String linha=buffer.readLine();
			 while(linha!=null) {
				 String[] vetLinha=linha.split(";");
				 if(vetLinha[6].equals(alunos)) {
					 Grupos grupo=new Grupos();
					 grupo.nome=vetLinha[0];
					 grupo.tema=vetLinha[1];
					 grupo.area=vetLinha[2];
					 grupo.subarea=vetLinha[3];
					 grupo.data=vetLinha[4];
					 grupo.codigo=vetLinha[5];
					 grupo.alunos=vetLinha[6];
					 grupo.Ra=vetLinha[7];
					 
					 ConGP.addFirst(grupo);
				 }
				 linha=buffer.readLine();
			 }
			 buffer.close();
			 isr.close();
			 fis.close();
		 }
	
		 return ConGP;
		
	}



	private Lista buscaData(String data) throws IOException {
		Lista ConGP = new Lista();
		 String path= System.getProperty ("user.home") + File.separator + "SistemaCadastro";
		 File arq= new File(path, "grupos.csv");
		 if(arq.exists() && arq.isFile()) {
			 FileInputStream fis=new FileInputStream(arq);
			 InputStreamReader isr= new InputStreamReader(fis);
			 BufferedReader buffer= new BufferedReader(isr);
			 String linha=buffer.readLine();
			 while(linha!=null) {
				 String[] vetLinha=linha.split(";");
				 if(vetLinha[4].equals(data)) {
					 Grupos grupo=new Grupos();
					 grupo.nome=vetLinha[0];
					 grupo.tema=vetLinha[1];
					 grupo.area=vetLinha[2];
					 grupo.subarea=vetLinha[3];
					 grupo.data=vetLinha[4];
					 grupo.codigo=vetLinha[5];
					 grupo.alunos=vetLinha[6];
					 grupo.Ra=vetLinha[7];
					 
					 ConGP.addFirst(grupo);
				 }
				 linha=buffer.readLine();
			 }
			 buffer.close();
			 isr.close();
			 fis.close();
		 }
	
		 return ConGP;
		
	}



	private Lista buscaSubarea(String subarea) throws IOException {
		Lista ConGP = new Lista();
		 String path= System.getProperty ("user.home") + File.separator + "SistemaCadastro";
		 File arq= new File(path, "grupos.csv");
		 if(arq.exists() && arq.isFile()) {
			 FileInputStream fis=new FileInputStream(arq);
			 InputStreamReader isr= new InputStreamReader(fis);
			 BufferedReader buffer= new BufferedReader(isr);
			 String linha=buffer.readLine();
			 while(linha!=null) {
				 String[] vetLinha=linha.split(";");
				 if(vetLinha[3].equals(subarea)) {
					 Grupos grupo=new Grupos();
					 grupo.nome=vetLinha[0];
					 grupo.tema=vetLinha[1];
					 grupo.area=vetLinha[2];
					 grupo.subarea=vetLinha[3];
					 grupo.data=vetLinha[4];
					 grupo.codigo=vetLinha[5];
					 grupo.alunos=vetLinha[6];
					 grupo.Ra=vetLinha[7];
					 ConGP.addFirst(grupo);
				 }
				 linha=buffer.readLine();
			 }
			 buffer.close();
			 isr.close();
			 fis.close();
		 }
	
		 return ConGP;
		
	}



	private Lista buscaArea(String area) throws IOException {
		Lista ConGP = new Lista();
		 String path= System.getProperty ("user.home") + File.separator + "SistemaCadastro";
		 File arq= new File(path, "grupos.csv");
		 if(arq.exists() && arq.isFile()) {
			 FileInputStream fis=new FileInputStream(arq);
			 InputStreamReader isr= new InputStreamReader(fis);
			 BufferedReader buffer= new BufferedReader(isr);
			 String linha=buffer.readLine();
			 while(linha!=null) {
				 String[] vetLinha=linha.split(";");
				 if(vetLinha[2].equals(area)) {
					 Grupos grupo=new Grupos();
					 grupo.nome=vetLinha[0];
					 grupo.tema=vetLinha[1];
					 grupo.area=vetLinha[2];
					 grupo.subarea=vetLinha[3];
					 grupo.data=vetLinha[4];
					 grupo.codigo=vetLinha[5];
					 grupo.alunos=vetLinha[6];
					 grupo.Ra=vetLinha[7];
					 
					 ConGP.addFirst(grupo);
				 }
				 linha=buffer.readLine();
			 }
			 buffer.close();
			 isr.close();
			 fis.close();
		 }
	
		 return ConGP;
		
	}



	private Grupos buscaGrupo(String codigo) throws IOException {
		Grupos grupo=new Grupos();
		 String path= System.getProperty ("user.home") + File.separator + "SistemaCadastro";
		 File arq= new File(path, "grupos.csv");
		 if(arq.exists() && arq.isFile()) {
			 FileInputStream fis=new FileInputStream(arq);
			 InputStreamReader isr= new InputStreamReader(fis);
			 BufferedReader buffer= new BufferedReader(isr);
			 String linha=buffer.readLine();
			 while(linha!=null) {
				 String[] vetLinha=linha.split(";");
				 if(vetLinha[5].equals(codigo)) {
					 grupo.nome=vetLinha[0];
					 grupo.tema=vetLinha[1];
					 grupo.area=vetLinha[2];
					 grupo.subarea=vetLinha[3];
					 grupo.data=vetLinha[4];
					 grupo.codigo=vetLinha[5];
					 grupo.alunos=vetLinha[6];
					 grupo.Ra=vetLinha[7];
					 
					 
					 break;
				 }
				 linha=buffer.readLine();
			 }
			 buffer.close();
			 isr.close();
			 fis.close();
		 }
		 if(grupo.codigo==null) {
				grupo=null;
			}
		 return grupo;
		 

	
	}
}