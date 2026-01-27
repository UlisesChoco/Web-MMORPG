enum GrpcStatus {
    INVALID_ARGUMENT = 3,
    ALREADY_EXISTS = 6,
    PERMISSION_DENIED = 7,
    INTERNAL = 13,
    UNAUTHENTICATED = 16
}

enum HttpStatus {
    BAD_REQUEST = 400,
    UNAUTHORIZED = 401,
    FORBIDDEN = 403,
    CONFLICT = 409,
    SERVICE_UNAVAILABLE = 503,
    INTERNAL_SERVER_ERROR = 500,
}

export class GrpcStatusCode {
    static grpcToHttp(grpcCode: number): number {
        switch (grpcCode) {
            case GrpcStatus.INVALID_ARGUMENT: return HttpStatus.BAD_REQUEST;
            case GrpcStatus.ALREADY_EXISTS: return HttpStatus.CONFLICT;
            case GrpcStatus.INTERNAL: return HttpStatus.SERVICE_UNAVAILABLE;
            case GrpcStatus.UNAUTHENTICATED: return HttpStatus.UNAUTHORIZED;
            case GrpcStatus.PERMISSION_DENIED: return HttpStatus.FORBIDDEN;
            default: return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}