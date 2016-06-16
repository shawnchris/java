package other;

import java.io.IOException;

public class D2L_Quiz {
	public interface Checksum {
		long getValue();
		void update(int b);
	}
	
	public abstract class OutputStream {
		public abstract void write(int b) throws IOException;
	}
	
	public class CheckedOutputStream extends OutputStream {
		private OutputStream out;
		private Checksum cksum;
		public CheckedOutputStream(OutputStream out, Checksum cksum) {
			this.out = out;
			this.cksum = cksum;
		}
		public void write(int b) throws IOException {
			out.write(b);
			cksum.update(b);
		}
		public Checksum getChecksum() {
			return cksum;
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
