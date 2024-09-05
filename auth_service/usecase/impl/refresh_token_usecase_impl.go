package usecase

import (
	"auth_service/model"
	"auth_service/usecase"
	"auth_service/utils/token_util"
	"context"
	"time"
)

type RefreshTokenUsecaseImpl struct {
	userRepository model.UserRepository
	contextTimeout time.Duration
}

func NewRefreshTokenUsecase(userRepository model.UserRepository, timeout time.Duration) usecase.RefreshTokenUsecase {
	return &RefreshTokenUsecaseImpl{
		userRepository: userRepository,
		contextTimeout: timeout,
	}
}

func (rtu *RefreshTokenUsecaseImpl) GetUserByID(c context.Context, id string) (model.User, error) {
	ctx, cancel := context.WithTimeout(c, rtu.contextTimeout)
	defer cancel()
	return rtu.userRepository.GetByID(ctx, id)
}

func (rtu *RefreshTokenUsecaseImpl) CreateAccessToken(user *model.User, secret string, expiry int) (accessToken string, err error) {
	return token_util.CreateAccessToken(user, secret, expiry)
}

func (rtu *RefreshTokenUsecaseImpl) CreateRefreshToken(user *model.User, secret string, expiry int) (refreshToken string, err error) {
	return token_util.CreateRefreshToken(user, secret, expiry)
}

func (rtu *RefreshTokenUsecaseImpl) ExtractIDFromToken(requestToken string, secret string) (string, error) {
	return token_util.ExtractIDFromToken(requestToken, secret)
}
