package usecase

import (
	"auth_service/model"
	"context"
)

type SignupUsecase interface {
	Create(c context.Context, user *model.User) error
	GetUserByEmail(c context.Context, email string) (model.User, error)
	GetUserByUsername(c context.Context, username string) (model.User, error)
	CreateAccessToken(user *model.User, secret string, expiry int) (accessToken string, err error)
	CreateRefreshToken(user *model.User, secret string, expiry int) (refreshToken string, err error)
}
