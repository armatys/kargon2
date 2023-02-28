PRETTY_PRINT = "\033[1;32m Building $(1)\033[0m\n"

XCODE_TOOLCHAIN = $(shell xcode-select --print-path)/Toolchains/XcodeDefault.xctoolchain
IOS_PLATFORM ?= iphoneos

IOS_SDK = $(shell xcrun -sdk ${IOS_PLATFORM} -show-sdk-path)

export PREFIX = ${CURDIR}/build/iOS/${ARCH}
LIBDIR = ${PREFIX}/lib

export CXX = ${XCODE_TOOLCHAIN}/usr/bin/clang++
export CC = ${XCODE_TOOLCHAIN}/usr/bin/clang
CROSSFLAGS = -arch ${ARCH} -isysroot ${IOS_SDK} -miphoneos-version-min=8.0
export CFLAGS = ${CROSSFLAGS} -O3 -fembed-bitcode -fvisibility=hidden -std=c89 -Wall -g -Iinclude -Isrc
HOST = arm-apple-darwin
LIBTOOLFLAGS = -arch_only ${ARCH}

all:
	@${MAKE} ios_arch ARCH=x86_64 IOS_PLATFORM=iphonesimulator >/dev/null
	@${MAKE} ios_arch ARCH=arm64 IOS_PLATFORM=iphoneos >/dev/null

clean:
	rm -rf build/
	${MAKE} -f argon.mk clean

ios_arch: status | ${LIBDIR}/libargon2.a

status:
	@printf $(call PRETTY_PRINT,$(ARCH)) 1>&2;

${LIBDIR}/libargon2.a:
	@printf $(call PRETTY_PRINT,$@) 1>&2;
	env CXXFLAGS="${CXXFLAGS}" \
	${MAKE} -f argon.mk clean && \
	${MAKE} -f argon.mk -j8 libargon2.a
	mkdir -p ${LIBDIR}
	mv libargon2.a ${LIBDIR}
