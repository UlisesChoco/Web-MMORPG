export interface GetRecentFatalitiesResponse {
    fatalities: Array<FatalityEntry>;
}

export interface FatalityEntry {
    playerId: number;
    enemyId: number;
    date: string;
}