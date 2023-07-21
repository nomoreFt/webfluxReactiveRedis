[![codecov](https://codecov.io/gh/nomoreFt/webfluxReactiveRedis/branch/master/graph/badge.svg?token=K87PF0W73A)](https://codecov.io/gh/nomoreFt/webfluxReactiveRedis)

![Codecov](https://codecov.io/gh/nomoreFt/webfluxReactiveRedis/branch/master/graphs/icicle.svg?token=K87PF0W73A)




# Redis docker image

docker run -p 6379:6379 redis


springboot3.x 에서부터는 redis starter를 추가 하면 lettuce, redis가 자동으로 깔린다.
lettuce 설정도 필요 없고 redis server만 설정해주면 된다.


# Redis UI 검사 툴

https://github.com/RedisInsight/RedisInsight


```java
Lettuce UI 툴 중 하나인 "RedisInsight"를 사용하는 방법을 안내해드리겠습니다. RedisInsight는 Redis 서버를 시각적으로 관리하고 모니터링할 수 있는 사용자 친화적인 UI 도구입니다.

RedisInsight 다운로드:
RedisInsight는 Redis Labs에서 제공하는 무료 도구입니다. Redis Labs 웹 사이트(https://redislabs.com/redisinsight/)에서 해당 운영 체제에 맞는 RedisInsight를 다운로드하여 설치합니다.

Redis 서버 연결:
RedisInsight를 실행한 후, "Connect to Redis" 버튼을 클릭하여 Redis 서버에 연결합니다. 연결할 Redis 서버의 호스트, 포트 및 인증 정보를 입력합니다.

Redis 데이터베이스 및 키 조회:
Redis 서버에 성공적으로 연결되면 Redis 데이터베이스와 키를 탐색할 수 있는 탐색기 화면이 표시됩니다. 여기서 데이터베이스를 선택하고 키를 탐색하여 Redis 데이터를 확인할 수 있습니다.

Redis 데이터 조작:
RedisInsight를 사용하여 데이터를 생성, 수정, 삭제할 수 있습니다. 키를 선택하고 값을 편집하거나 삭제할 수 있습니다.

모니터링 및 성능 분석:
RedisInsight는 Redis 서버의 성능 모니터링을 제공하며, 실시간 그래프와 지표를 통해 Redis 클러스터의 상태를 확인할 수 있습니다. 성능 통계, 메모리 사용량, 커맨드 실행 등의 정보를 확인할 수 있습니다.

이와 같이 RedisInsight를 사용하여 Redis 서버를 쉽게 관리하고 모니터링할 수 있습니다. 다른 Lettuce UI 툴도 있을 수 있으며, Redis Labs 외에도 다른 개발자들이 개발한 도구도 있을 수 있으니 해당 도구를 검색하여 필요에 맞게 선택하실 수 있습니다.

```

![](../../../../../var/folders/67/v2hfg63s6hqgn0fzpz3z454r0000gn/T/TemporaryItems/NSIRD_screencaptureui_jHBbDh/스크린샷 2023-07-20 오후 10.49.49.png)




# Redis test

testImplementation 'it.ozimov:embedded-redis:0.7.3'


```java
@TestConfiguration
public class EmbeddedRedisExtension {

    private RedisServer redisServer;
    @PostConstruct
    public void postConstruct() {
        redisServer = RedisServer.builder()
                .port(6380)
                .setting("maxmemory 128M")
                .build();

        redisServer.start();
    }

    @PreDestroy
    public void preDestroy() {
        redisServer.stop();
    }

}


//Redis Embedded Test가 가능
@SpringBootTest(classes = {EmbeddedRedisExtension.class})
class RedisLinkRepositoryTest {

}


```
