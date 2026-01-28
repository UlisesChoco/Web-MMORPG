import { MiddlewareConsumer, Module, NestModule, RequestMethod } from '@nestjs/common';
import { AuthModule } from './auth/auth.module';
import { AuthMiddleware } from './auth/auth.middleware';
import { PlayerClassModule } from './class/module/player-class.module';
import { PlayerClassModifiersModule } from './class/module/player-class-modifiers.module';
import { CombatModule } from './combat/module/combat.module';
import { CombatHistoryModule } from './combat/module/combat-history.module';
import { FatalCombatReplayModule } from './combat/module/fatal-combat-replay.module';

@Module({
  imports: [
    AuthModule,
    PlayerClassModule, PlayerClassModifiersModule,
    CombatModule, CombatHistoryModule, FatalCombatReplayModule,
  ],
})
export class AppModule implements NestModule {
  configure(consumer: MiddlewareConsumer) {
    consumer
      .apply(AuthMiddleware)
      .exclude({ path: '/auth/*path', method: RequestMethod.ALL })
      .forRoutes('*');
  }
}
