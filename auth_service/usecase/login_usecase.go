package usecase

import (
	"auth_service/model"
	"context"
)

type LoginUsecase interface {
	GetUserByUsername(ctx context.Context, username string) (user model.User, err error)
	CreateAccessToken(user *model.User, secret string, expiry int) (accessToken string, err error)
	CreateRefreshToken(user *model.User, secret string, expiry int) (refreshToken string, err error)
}
