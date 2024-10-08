package payload

const (
	SUCCESS               = 200
	BAD_REQUEST           = 400
	UNAUTHORIZED          = 401
	INTERNAL_SERVER_ERROR = 500
)

type ApiResponse struct {
	Code    int         `json:"code"`
	Message string      `json:"message,omitempty"`
	Result  interface{} `json:"result"`
}

func NewDBErrorResponse(msg string) *ApiResponse {
	return &ApiResponse{
		Code:    INTERNAL_SERVER_ERROR,
		Message: msg,
		Result:  nil,
	}
}

func NewServerErrorResponse(msg string) *ApiResponse {
	return &ApiResponse{
		Code:    INTERNAL_SERVER_ERROR,
		Message: msg,
		Result:  nil,
	}
}

func NewBadRequestResponse(msg string) *ApiResponse {
	return &ApiResponse{
		Code:    BAD_REQUEST,
		Message: msg,
		Result:  nil,
	}
}

func NewUnauthorizedResponse(msg string) *ApiResponse {
	return &ApiResponse{
		Code:    UNAUTHORIZED,
		Message: msg,
		Result:  nil,
	}
}

func NewDataResponse(result interface{}) *ApiResponse {
	return &ApiResponse{
		Code:    SUCCESS,
		Message: "",
		Result:  result,
	}
}
