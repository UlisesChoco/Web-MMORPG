export class ProcessCombatResponseDTO {
    combatId: number;
    wasFatal: boolean;
    totalTurns: number;
    turns: Array<CombatTurnGrpc>;
    loot: LootGrpc;
}

export class CombatTurnGrpc {
    turnNumber: number;
    playerAction: ActionGrpc;
    enemyAction: ActionGrpc;
    playerStateAfter: StateGrpc;
    enemyStateAfter: StateGrpc;
}

export class ActionGrpc {
    turnAction: string;
    turnResult: string;
    damage: number;
    critical: boolean;
}

export class StateGrpc {
    hp: number;
    stamina: number;
    accuracy: number;
    evasion: number;
}

export class LootGrpc {
    gold: number;
    experience: number;
    itemId: number | undefined;
}