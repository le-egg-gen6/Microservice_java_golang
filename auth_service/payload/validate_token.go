package payload

type ValidateTokenRequest struct {
	AccessToken string `json:"accessToken"`
}

type ValidateTokenResponse struct {
	Authorized bool `json:"authorized"`
}
