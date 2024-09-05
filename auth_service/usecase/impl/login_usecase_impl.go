package usecase

import (
	"auth_service/model"
	"auth_service/usecase"
	"auth_service/utils/token_util"
	"context"
	"time"
)

type LoginUsecaseImpl struct {
	userRepository model.UserRepository
	contextTimeout time.Duration
}

func NewLoginUsecase(userRepository model.UserRepository, contextTimeout time.Duration) usecase.LoginUsecase {
	return &LoginUsecaseImpl{
		userRepository: userRepository,
		contextTimeout: contextTimeout,
	}
}

func (lu *LoginUsecaseImpl) GetUserByUsername(ctx context.Context, username string) (user model.User, err error) {
	ctx, cancel := context.WithTimeout(ctx, lu.contextTimeout)
	defer cancel()
	return lu.userRepository.GetByUsername(ctx, username)
}

func (lu *LoginUsecaseImpl) CreateAccessToken(user *model.User, secret string, expiry int) (accessToken string, err error) {
	return token_util.CreateAccessToken(user, secret, expiry)
}

func (lu *LoginUsecaseImpl) CreateRefreshToken(user *model.User, secret string, expiry int) (refreshToken string, err error) {
	return token_util.CreateRefreshToken(user, secret, expiry)
}
