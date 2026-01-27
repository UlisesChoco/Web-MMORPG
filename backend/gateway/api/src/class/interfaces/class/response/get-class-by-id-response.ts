export interface GetClassByIdResponse {
    message: string;
    data: ClassData;
}

export interface ClassData {
    id: number;
    name: string;
    description: string;
    critRate: number;
    critDamage: number;
    hp: number;
    atk: number;
    def: number;
    stamina: number;
    accuracy: number;
    evasion: number;
}