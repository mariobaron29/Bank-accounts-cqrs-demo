package com.mariobaron.bankaccounts;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.java.Log;
import org.reactivecommons.async.impl.communications.TopologyCreator;
import org.reactivecommons.async.impl.config.ConnectionFactoryProvider;
import org.reactivecommons.async.impl.config.RabbitMqConfig;
import org.reactivecommons.async.impl.config.RabbitProperties;
import org.reactivecommons.async.impl.config.props.BrokerConfigProps;
import org.reactivecommons.async.impl.converters.MessageConverter;
import org.reactivecommons.utils.ObjectMapper;
import org.reactivecommons.utils.ObjectMapperImp;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;
import reactor.rabbitmq.*;
import reactor.util.retry.Retry;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.util.logging.Level;

@Log
@Configuration
@Import(RabbitMqConfig.class)
public class ControllerConfig {

    private static final String SENDER_TYPE = "sender";

    @Value("${spring.application.name}")
    private String appName;

    @Value("${elk.rabbitmq.host}")
    private String elkHost;

    @Value("${elk.rabbitmq.username}")
    private String elkUserName;

    @Value("${elk.rabbitmq.password}")
    private String elkPassword;

    @Value("${elk.rabbitmq.virtual_host}")
    private String elkVH;

    @Value("${elk.rabbitmq.port}")
    private Integer elkPort;

    @Value("${component.name}")
    private String componentName;

    @Value("${service.name}")
    private String serviceName;

    @Value("${operation}")
    private String operation;

    @Value("${rabbit.ssl.protocol}")
    private String tls;

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapperImp();
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(TcpClient.newConnection())))
                .build();
    }

 /*   @Bean("ELK")
    @Primary
    public ConnectionFactoryProvider appConnectionFactoryELK(RabbitProperties properties) throws KeyManagementException,
            NoSuchAlgorithmException {
        final ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(elkHost);
        factory.setUsername(elkUserName);
        factory.setPassword(elkPassword);
        factory.setVirtualHost(elkVH);
        factory.setPort(elkPort);
        factory.setRequestedHeartbeat(1);
        factory.setAutomaticRecoveryEnabled(true);
        factory.setTopologyRecoveryEnabled(true);
        factory.useSslProtocol(tls);
        return () -> factory;
    }

    @Bean("senderElk")
    @Primary
    public CustomReactiveMessageSender customReactiveSender(@Qualifier("ELK") ConnectionFactoryProvider provider,
                                                            MessageConverter converter, BrokerConfigProps brokerConfigProps,
                                                            RabbitProperties rabbitProperties) {

        final Mono<Connection> senderConnection = createConnectionMonoElk(provider.getConnectionFactory(), appName,
                SENDER_TYPE);
        final ChannelPoolOptions channelPoolOptions = new ChannelPoolOptions();
        final PropertyMapper map = PropertyMapper.get();

        map.from(rabbitProperties.getCache().getChannel()::getSize).whenNonNull()
                .to(channelPoolOptions::maxCacheSize);

        final ChannelPool channelPool = ChannelPoolFactory.createChannelPool(
                senderConnection,
                channelPoolOptions
        );

        final Sender sender = RabbitFlux.createSender(new SenderOptions()
                .channelPool(channelPool)
                .resourceManagementChannelMono(channelPool.getChannelMono()
                        .transform(Utils::cache)));

        return new CustomReactiveMessageSender(sender, brokerConfigProps.getAppName(), converter,
                new TopologyCreator(sender));
    }

    Mono<Connection> createConnectionMonoElk(@Qualifier("ELK") ConnectionFactory factory, String connectionPrefix,
                                             String connectionType) {
        return Mono.fromCallable(() -> factory.newConnection(connectionPrefix + " " + connectionType
                + " elk"))
                .doOnError(err -> log.log(Level.SEVERE,
                        "Error creating connection to RabbitMq Broker. Starting retry process...", err))
                .retryWhen(Retry.backoff(Long.MAX_VALUE, Duration.ofMillis(300))
                        .maxBackoff(Duration.ofMillis(3000)))
                .cache();
    }*/


}
