################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../problem56.cpp 

OBJS += \
./problem56.o 

CPP_DEPS += \
./problem56.d 


# Each subdirectory must supply rules for building sources it contributes
problem56.o: ../problem56.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: Cross G++ Compiler'
	g++ -I/usr/local/lib -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"problem56.d" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


