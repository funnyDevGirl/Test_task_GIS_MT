package hexlet.code;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Synchronized;
import java.util.concurrent.TimeUnit;
import static jakarta.persistence.GenerationType.IDENTITY;


@Getter
public class CrptApi {

    /**
     * Accepts a time interval and a limit on the number of requests.
     * Initializes the fields and sets the start time of the last request.
     */
    private final TimeUnit timeUnit;
    private final int requestLimit;
    private int currentRequests;
    private long lastRequestTime;


    public CrptApi(TimeUnit timeUnit, int requestLimit) {
        this.timeUnit = timeUnit;
        this.requestLimit = requestLimit;
        this.currentRequests = 0;
        this.lastRequestTime = System.currentTimeMillis();
    }

    /**
     * Checks if enough time has passed since the last request.
     * If it has passed, the request counter is reset and the new time of the last request is set.
     * Checks whether the limit on the number of requests has been exceeded.
     * If not exceeded, a document is created and the request counter is incremented.
     * Otherwise, the method waits until the end of the time interval and then calls itself again to process the request.
     * @param document
     * @param signature
     */
    @Synchronized
    public void createProductDocument(Document document, String signature) throws InterruptedException {

        // время текущего запроса:
        long currentTime = System.currentTimeMillis();

        // проверяем, что мы ещё находимся в заданном отрезке:
        if (currentTime - lastRequestTime < timeUnit.toMillis(1)) {

            // если кол-во запросов превышено, то засыпаем на sleepTime,
            // обновляем время последнего запроса, когда просыпаемся,
            // устанавниваем счетчику запросов 1:
            if (currentRequests >= requestLimit) {
                long sleepTime = timeUnit.toMillis(1) - (currentTime - lastRequestTime);
                Thread.sleep(sleepTime);
                lastRequestTime = System.currentTimeMillis();
                currentRequests = 1;

            //если кол-во ещё не превышено, то добавляем 1 запрос:
            } else {

                //тут какой-то код создания документа

                currentRequests++;
            }

        // если отрезок времени уже превышен, но обновляем время последнего запроса
        // и устанавливаем счетчику запросов 1:
        } else {
            lastRequestTime = currentTime;
            currentRequests = 1;
        }

        System.out.println("Product document created successfully with code №: " + document.hashCode());
    }


    @Entity
    @Getter
    @Setter
    @EqualsAndHashCode
    public static class Document {

        @Id
        @GeneratedValue(strategy = IDENTITY)
        private long id;

        @NotBlank
        private String name;


        public Document(String name) {
            this.name = name;
        }
    }



//    public static void main(String[] args) throws InterruptedException {
//        CrptApi crptApi = new CrptApi(TimeUnit.SECONDS, 5);
//        for (int i = 0; i < 30; i++) {
//            Document document = new Document("Document №" + i);
//            crptApi.createProductDocument(document, document.hashCode() + "");
//        }
//    }
}
