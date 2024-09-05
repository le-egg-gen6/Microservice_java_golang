package usecase

import (
	"auth_service/model"
	"auth_service/usecase"
	"auth_service/utils/token_util"
	"context"
	"time"
)

type SignupUsecaseImpl struct {
	userRepository model.UserRepository
	contextTimeout time.Duration
}

func NewSignupUsecase(userRepository model.UserRepository, contextTimeout time.Duration) usecase.SignupUsecase {
	return &SignupUsecaseImpl{
		userRepository: userRepository,
		contextTimeout: contextTimeout,
	}
}

func (su *SignupUsecaseImpl) Create(c context.Context, user *model.User) error {
	ctx, cancel := context.WithTimeout(c, su.contextTimeout)

	defer cancel()

	return su.userRepository.Create(ctx, user)
}

func (su *SignupUsecaseImpl) GetUserByUsername(c context.Context, username string) (model.User, error) {
	ctx, cancel := context.WithTimeout(c, su.contextTimeout)

	defer cancel()

	return su.userRepository.GetByUsername(ctx, username)
}

func (su *SignupUsecaseImpl) GetUserByEmail(c context.Context, email string) (model.User, error) {
	ctx, cancel := context.WithTimeout(c, su.contextTimeout)

	defer cancel()

	return su.userRepository.GetByEmail(ctx, email)
}

func (su *SignupUsecaseImpl) CreateAccessToken(user *model.User, secret string, expiry int) (accessToken string, err error) {
	return token_util.CreateAccessToken(user, secret, expiry)
}

func (su *SignupUsecaseImpl) CreateRefreshToken(user *model.User, secret string, expiry int) (refreshToken string, err error) {
	return token_util.CreateRefreshToken(user, secret, expiry)
}
