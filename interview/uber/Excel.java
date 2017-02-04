package interview.uber;

import java.util.*;

public class Excel {
	// Max sheets: unlimited
	
	enum DataType {
		Number,
		String,
		Date,
		Formula
	}
	
	class Cell {
		DataType type;
		double num;
		String str;
		Date date;
		List<Cell> predecessors;
		List<Character> operaters;
		Set<Cell> successors; 
		
		public Cell() {
			type = DataType.String;
			num = 0;
			str = "";
			date = new Date(0);
			predecessors = new ArrayList<Cell>();
			operaters = new ArrayList<Character>();
			successors = new HashSet<Cell>();
		}
		
		public boolean setType(DataType type) {
			if (this.type != type) {
				// Transform
			}
			this.type = type;
			return true;
		}
		
		public boolean setString(String str) {
			this.predecessors.clear();
			this.type = DataType.String;
			this.str = str;
			return true;
		}
		
		public boolean setNumber(double num) {
			this.predecessors.clear();
			this.type = DataType.Number;
			this.num = num;
			return true;
		}
		
		public boolean setFormula(List<Cell> fcells, List<Character> operaters) {
			if (fcells == null || operaters == null || fcells.size() == 0 
					|| fcells.size() - operaters.size() != 1)
				return false;
			//if (fcells.get(0).type != DataType.Number)
			//	return false;
			
			// Check circular dependency
			Queue<Cell> queue = new LinkedList<>();
			Set<Cell> visited = new HashSet<>();
			for (Cell c: fcells) {
				queue.add(c);
			}
			while (!queue.isEmpty()) {
				Cell cell = queue.poll();
				if (cell == this) {
					return false;
				}
				if (visited.contains(cell)) {
					continue;
				}
				visited.add(cell);
				for (Cell child: cell.predecessors) {
					if (!visited.contains(child)) {
						queue.add(child);
					}
				}
			}
			
			double result = fcells.get(0).num;
			for (int i = 1; i < fcells.size(); i++) {
				char op = operaters.get(i - 1);
				//if (fcells.get(i).type != DataType.Number)
				//	return false;
				result = evaluate(result, op, fcells.get(i).num);
			}
			
			// Update references
			this.predecessors.clear();
			for (Cell cell: fcells) {
				cell.successors.add(this);
				this.predecessors.add(cell);
			}
			
			this.num = result;
			this.type = DataType.Formula;
			return true;
		}
		
		private double evaluate(double d1, char op, double d2) {
			if (op == '+') {
				return d1 + d2;
			}
			else if (op == '-') {
				return d1 - d2;
			}
			else if (op == '*') {
				return d2 * d2;
			}
			else if (op == '/') {
				return d1 / d2;
			}
			return 0;
		}
		
		public String toString() {
			if (type == DataType.Number) {
				return String.valueOf(num);
			}
			else if (type == DataType.Date) {
				return date.toString();
			}
			else if (type == DataType.Formula) {
				// temp solution
				return String.valueOf(num);
			}
			
			return str;
		}
	}
	
	class Sheet {
		// Max rows: 1M
		// Max cols: 16K
		Map<Integer, Map<Integer, Cell>> data = new HashMap<Integer, Map<Integer, Cell>>();
		
		public Cell getCell(int row, int col) {
			Map<Integer, Cell> r = data.get(row);
			if (r == null) {
				r = new HashMap<Integer, Cell>();
				data.put(row, r);
			}
			Cell c = r.get(col);
			if (c == null) {
				c = new Cell();
				r.put(col, c);
			}
			return c;
		}
		
		public void setCell(int row, int col, double num) {
			Cell c = getCell(row, col);
			c.setNumber(num);
		}
		public void setCell(int row, int col, String str) {
			Cell c = getCell(row, col);
			c.setString(str);
		}
		public boolean setCell(int row, int col, List<Cell> fcells, List<Character> operaters) {
			Cell c = getCell(row, col);
			return c.setFormula(fcells, operaters);
		}
		public void print() {
			for (int row: data.keySet()) {
				Map<Integer, Cell> r = data.get(row);
				System.out.print("Row(" + row + "):");
				for (int col: r.keySet()) {
					System.out.print(" Col(" + col +"): " + r.get(col));
				}
				System.out.println();
			}
			System.out.println("------------------------------------------------------");
		}
		
	}
	
	public static void main(String[] args) {
		Excel e = new Excel();
		Sheet s = e.new Sheet();
		s.setCell(1, 1, 1);
		s.setCell(1, 2, 2);
		s.print();
		s.setCell(2, 2, "Hello");
		s.print();
		List<Cell> fcells = new ArrayList<>();
		fcells.add(s.getCell(1, 1));
		fcells.add(s.getCell(1, 2));
		List<Character> operaters = new ArrayList<>();
		operaters.add('+');
		s.setCell(2, 3, fcells, operaters);
		s.print();
		fcells.remove(0);
		fcells.add(s.getCell(2, 3));
		System.out.println(s.setCell(1, 1, fcells, operaters));
		s.print();
	}

}
