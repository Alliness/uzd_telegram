import api.TelegramApi;
import core.ConfigLoader;
import core.Serializable;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App {
    private static final Logger log      = Logger.getLogger(App.class);
    private static       App    instance = new App();

    private final TelegramApi                    api;
    private       List<ScheduledExecutorService> services;

    public static App getInstance() {
        return instance;
    }

    private App() {
        api = new TelegramApi();
        services = new ArrayList<>();

        registerTask(api::getUpdates, Long.parseLong(ConfigLoader.getInstance().getProperty("api.updates.delay")));
        registerTask(api::messageNotify, 500);
        api.messageNotify();
    }

    private void registerTask(Runnable task, long delay) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
        service.scheduleAtFixedRate(task, 0, delay, TimeUnit.MILLISECONDS);
        services.add(service);
    }

}
