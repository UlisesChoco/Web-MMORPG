package com.chocolatada.combat;

import com.chocolatada.combat.domain.Combat;
import com.chocolatada.combat.service.domain.ICombatService;
import io.grpc.StatusRuntimeException;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CombatApplication {

    private static ICombatService combatService;

    public CombatApplication(ICombatService combatService) {
        CombatApplication.combatService = combatService;
    }

	public static void main(String[] args) {
		SpringApplication.run(CombatApplication.class, args);
        try {
            Combat combat = combatService.processCombat(1L, 1L);
            System.out.println("\n"+combat+"\n");
        } catch (StatusRuntimeException e) {
            System.err.println("gRPC Error: " + e);
        }
    }

}
