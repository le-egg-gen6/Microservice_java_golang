package payload

type ValidateTokenRequest struct {
	AccessToken string `json:"accessToken"`
}

type ValidateTokenResponse struct {
	Authenticated bool `json:"authenticated"`
}
