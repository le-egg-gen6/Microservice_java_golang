package dto

import "github.com/golang-jwt/jwt"

type JwtCustomClaims struct {
	ID string `json:"id"`
	jwt.StandardClaims
}

type JwtCustomRefreshClaims struct {
	ID string `json:"id"`
	jwt.StandardClaims
}
