package util.input.keyboard;

public class KeySequence {
	private KeySequenceStep[] steps;
	KeySequence(KeySequenceStep[] steps){
		this.steps = steps;
	}
	public void execute(){
		for(KeySequenceStep step:steps){
			step.execute();
		}
	}
	
	public KeySequenceStep[] getSteps() {
		return steps;
	}


	public interface KeySequenceStep{
		public void execute();
	}

	public static class WaitStep implements KeySequenceStep {
		private long time;
		public WaitStep(long time) {//in ms
			this.time = time;
		}

		public long getTime() {
			return time;
		}
		
		public String toString(){
			return "Wait "+time+" ms";
		}

		@Override
		public void execute() {
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
			}
		}

	}
}
