package middleware

import (
	"auth_service/payload"
	"github.com/gin-gonic/gin"
	"net/http"
)

func InternalApiMiddlewareFilter(secret string) gin.HandlerFunc {
	return func(c *gin.Context) {
		internalHeader := c.Request.Header.Get("Internal")
		if internalHeader == "" {
			c.JSON(
				http.StatusUnauthorized,
				payload.NewUnauthorizedResponse("Invalid Token"),
			)
			c.Abort()
			return
		}
		isInternal := internalHeader == secret
		if isInternal {
			c.Next()
			return
		}
		c.JSON(
			http.StatusUnauthorized,
			payload.NewUnauthorizedResponse("Invalid Token"),
		)
		c.Abort()
	}
}
