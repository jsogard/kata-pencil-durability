package pencil_durability;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class IOManager {

	private Map<String, Paper> papers;
	private Map<String, Pencil> pencils;
	
	private static final String[] commandTemplates = new String[]{
			"help",
			"new_paper [name]",
			"new_pencil [name]",
			"get_papers",
			"get_pencils",
			"read [paper]",
			"info [pencil]",
			"sharpen [pencil]",
			"write [paper] [pencil] \"[message]\"",
			"edit [paper] [pencil] [index] \"[message]\"",
			"erase [paper] [pencil] \"[message]\"",
			"quit"
	};
	
	public int handleCommand(String command){
		String[] words = extractWords(command);
		int outCode = 0;
		try{
		if(words[0].equals("help")){
			displayCommands();
		} else if(words[0].equals("new_paper")){
			outCode = newPaper(words[1]);
			displayPapers();
		} else if(words[0].equals("new_pencil")){
			outCode = newPencil(words[1]);
			displayPencils();
		} else if(words[0].equals("get_papers")){
			displayPapers();
		} else if(words[0].equals("get_pencils")){
			displayPencils();
		} else if(words[0].equals("read")){
			outCode = readPaper(words[1]);
		} else if(words[0].equals("info")){
			outCode = infoPencil(words[1]);
		} else if(words[0].equals("sharpen")){
			outCode = sharpen(words[1]);
		} else if(words[0].equals("write")){
			outCode = write(words[1], words[2], words[3]);
		} else if(words[0].equals("edit")){
			outCode = edit(words[1], words[2], new Integer(words[3]), words[4]);
		} else if(words[0].equals("erase")){
			outCode = erase(words[1], words[2], words[3]);
		} else if(words[0].equals("quit")){
			return 1;
		}else {
			System.out.println("Command not understood");
		}
		System.out.println(handleError(outCode));
		} catch(Exception e){
			System.out.println("Command not understood");
		}
			
		return 0;
	}
	
	public IOManager(){
		papers = new HashMap<String, Paper>();
		pencils = new HashMap<String, Pencil>();
	}
	
	private void displayMenu(String menuName, String[] options){
		String menu;
		int i;
		int menuWidth = menuName.length();
		for(String option : options)
			if(menuWidth < option.length())
				menuWidth = option.length();
		menuWidth += 2;
		menu = "+-" + menuName;
		for(i = 1 + menuName.length(); i < menuWidth; i++) menu += '-';
		menu += "+\n|";
		for(i = 0; i < menuWidth; i++) menu += ' ';
		menu += "|\n";
		for(String option : options){
			menu += "| ";
			menu += option;
			for(i = option.length() + 1; i < menuWidth; i++) menu += ' ';
			menu += "|\n";
		}
		menu += "|";
		for(i = 0; i < menuWidth; i++) menu += ' ';
		menu += "|\n+";
		for(i = 0; i < menuWidth; i++) menu += '-';
		menu += "+\n";
		System.out.println(menu);
	}
	
	private void displayMenu(String menuName, Map<String, ?> options){
		String[] optionsArr = new String[options.size()];
		displayMenu(menuName, options.keySet().toArray(optionsArr));
	}
	
	private void displayCommands(){
		displayMenu("Commands",	commandTemplates);
	}
	
	private void displayPencils(){
		displayMenu("Pencils", pencils);
	}
	
	private void displayPapers(){
		displayMenu("Papers", papers);
	}
	
	private int readPaper(String name){
		if(!papers.containsKey(name)) return 2;
		printPaper(name, papers.get(name));
		return 0;
	}
	
	private int infoPencil(String name){
		if(!pencils.containsKey(name)) return 1;
		Pencil pencil = pencils.get(name); 
		displayMenu(name, new String[]{
				"Point: " + pencil.getPointDurability(),
				"Eraser: " + pencil.getEraserDurability(),
				"Length: " + pencil.getLength()
		});
		return 0;
	}
	
	private int newPaper(String name){
		if(papers.containsKey(name)) return 2;
		papers.put(name, new Paper());
		return 0;
	}
	
	private int newPencil(String name){
		if(pencils.containsKey(name)) return 1;
		pencils.put(name,  new Pencil());
		return 0;
	}
	
	private int sharpen(String name){
		if(!pencils.containsKey(name)) return 1;
		if(!pencils.get(name).sharpen()) return 3;
		System.out.println(String.format("%s is sharpened!", name));
		return 0;
	}
	
	private int write(String paperName, String pencilName, String message){
		if(!pencils.containsKey(pencilName)) return 1;
		if(!papers.containsKey(paperName)) return 2;
		Paper paper = papers.get(paperName);
		boolean success = paper.write(message, pencils.get(pencilName));
		printPaper(paperName, paper);
		
		return success ? 0 : 4;
	}
	
	private void printPaper(String name, Paper paper){
		System.out.println(name + ":> \"" + paper.getText() + '"');
		String line = "";
		for(int i = 0; i < name.length() + 4; i++) line += ' ';
		for(int i = 0; i < paper.getText().length(); i++){
			if(i % 10 == 0) line += "0";
			else if(i % 5 == 0) line += "5";
			else line += "'";
		}
		System.out.println(line);
	}
	
	private int edit(String paperName, String pencilName, int index, String message){
		if(!pencils.containsKey(pencilName)) return 1;
		if(!papers.containsKey(paperName)) return 2;
		Paper paper = papers.get(paperName);
		Pencil pencil = pencils.get(pencilName);
		int outCode = 0;
		if(!paper.edit(message, index, pencil)){
			if(pencil.getPointDurability() == 0)
				outCode = 4;
			else
				outCode = 5;
		}
		printPaper(paperName, paper);
		return outCode;
	}
	
	private int erase(String paperName, String pencilName, String message){
		if(!pencils.containsKey(pencilName)) return 1;
		if(!papers.containsKey(paperName)) return 2;
		Paper paper = papers.get(paperName);
		Pencil pencil = pencils.get(pencilName);
		int outCode = 0;
		if(!paper.erase(message, pencil)){
			if(pencil.getEraserDurability() == 0)
				outCode = 6;
			else
				outCode = 7;
		}
		printPaper(paperName, paper);
		return outCode;
	}
	
	
	
	private String[] extractWords(String command){
		ArrayList<String> words = new ArrayList<String>();
		
		int start = 0, end;
		boolean inQuotes = false;
		for(end = 1; end < command.length(); end++){
			if(inQuotes && command.charAt(end) == '"'){
				inQuotes = false;
				words.add(command.substring(start+1, end));
			}
			else if(command.charAt(start) == '"'){
				inQuotes = true;
			}
			else if(command.charAt(end) == ' ' && end > start){
				words.add(command.substring(start, end));
				start = end+1;
			}
		}
		words.add(command.substring(start));
		
		return words.toArray(new String[words.size()]);
	}
	
	private String handleError(int outCode){
		String out;
		switch(outCode){
		case 1:
			out = "Pencil of that name does not exist";
			break;
		case 2:
			out = "Paper of that name does not exist";
			break;
		case 3:
			out = "Pencil could not be sharpened";
			break;
		case 4:
			out = "Pencil's point is broken";
			break;
		case 5:
			out = "index is out of bounds";
			break;
		case 6:
			out = "Eraser is broken";
			break;
		case 7:
			out = "Substring could not be found";
			break;
		default:
			out = "";
		}
		return out;
	}
	
	
	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		IOManager io = new IOManager();
		io.displayCommands();
		while(true){
			System.out.print("> ");
			if(io.handleCommand(sc.nextLine()) == 1) {
				sc.close();
				return;
			}
		}
	}
}
