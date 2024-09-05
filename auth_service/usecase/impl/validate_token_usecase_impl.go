package usecase

import (
	"auth_service/model"
	"auth_service/usecase"
	"auth_service/utils/token_util"
	"context"
	"time"
)

type ValidateTokenUsecaseImpl struct {
	userRepository model.UserRepository
	contextTimeout time.Duration
}

func NewValidateTokenUsecase(userRepository model.UserRepository, contextTimeout time.Duration) usecase.ValidateTokenUsecase {
	return &ValidateTokenUsecaseImpl{
		userRepository: userRepository,
		contextTimeout: contextTimeout,
	}
}

func (vtu *ValidateTokenUsecaseImpl) GetUserByID(ctx context.Context, id string) (model.User, error) {
	ctx, cancel := context.WithTimeout(ctx, vtu.contextTimeout)
	defer cancel()
	return vtu.userRepository.GetByID(ctx, id)
}

func (vtu *ValidateTokenUsecaseImpl) ExtractIDFromToken(requestToken string, secret string) (string, error) {
	return token_util.ExtractIDFromToken(requestToken, secret)
}
