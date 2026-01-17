package com.chocolatada.combat.configuration;

import com.chocolatada.combat.grpc.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.client.GrpcChannelFactory;

@Configuration
@RequiredArgsConstructor
public class GrpcClientConfiguration {
    private final GrpcChannelFactory channelFactory;

    //por ahora lo dejo asi para testear,
    //pero deberia dockerizar y cambiar a direcciones docker
    private final String ENEMY_SERVICE_ADDRESS = "localhost:9097";

    private final String PLAYER_SERVICE_ADDRESS = "localhost:9095";

    private final String PLAYER_CLASS_SERVICE_ADDRESS = "localhost:9091";

    private final String LOOT_SERVICE_ADDRESS = "localhost:9094";

    private final String INVENTORY_SERVICE_ADDRESS = "localhost:9093";

    @Bean
    EnemyServiceGrpc.EnemyServiceBlockingStub enemyStub() {
        return EnemyServiceGrpc
                .newBlockingStub(channelFactory.createChannel(ENEMY_SERVICE_ADDRESS));
    }

    @Bean
    PlayerServiceGrpc.PlayerServiceBlockingStub playerStub() {
        return PlayerServiceGrpc
                .newBlockingStub(channelFactory.createChannel(PLAYER_SERVICE_ADDRESS));
    }

    @Bean
    PlayerClassServiceGrpc.PlayerClassServiceBlockingStub classStub() {
        return PlayerClassServiceGrpc
                .newBlockingStub(channelFactory.createChannel(PLAYER_CLASS_SERVICE_ADDRESS));
    }

    @Bean
    LootServiceGrpc.LootServiceBlockingStub lootStub() {
        return LootServiceGrpc
                .newBlockingStub(channelFactory.createChannel(LOOT_SERVICE_ADDRESS));
    }

    @Bean
    InventoryServiceGrpc.InventoryServiceBlockingStub inventoryStub() {
        return InventoryServiceGrpc
                .newBlockingStub(channelFactory.createChannel(INVENTORY_SERVICE_ADDRESS));
    }
}
