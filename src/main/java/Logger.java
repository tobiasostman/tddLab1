public class Logger implements ILogger {
    @Override
    public void log(Integer number) {
        System.out.println(number);
    }
}
