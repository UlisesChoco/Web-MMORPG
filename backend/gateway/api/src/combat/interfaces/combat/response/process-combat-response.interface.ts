export interface ProcessCombatResponse {
    combatId: number;
    wasFatal: boolean;
    totalTurns: number;
    turns: Array<CombatTurnGrpc>;
    loot: LootGrpc;
}

export interface CombatTurnGrpc {
    turnNumber: number;
    playerAction: ActionGrpc;
    enemyAction: ActionGrpc;
    playerStateAfter: StateGrpc;
    enemyStateAfter: StateGrpc;
}

export interface ActionGrpc {
    turnAction: TurnActionGrpc;
    turnResult: TurnResultGrpc;
    damage: number;
    critical: boolean;
}

export enum TurnActionGrpc {
    BASIC_ATTACK = 0
}

export enum TurnResultGrpc {
    HIT = 0,
    MISS = 1,
    CRITICAL_HIT = 2,
    DODGE = 3
}

export interface StateGrpc {
    hp: number;
    stamina: number;
    accuracy: number;
    evasion: number;
}

export interface LootGrpc {
    gold: number;
    itemId: number | null;
}