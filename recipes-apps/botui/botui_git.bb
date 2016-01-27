# TODO: not finished -  I went to work on libkar
inherit qt4x11 cmake

PN="botui"
PR="6"

SRCREV = "d23873728693f13b62c73c0b66787e687ee85db2"

SRC_URI="git://github.com/kipr/botui.git;branch=libwallaby-new-sensors \
	file://botui.service \
"

EXTRA_OECMAKE += "-DBITBAKE_BS=1 -DCMAKE_SYSROOT=${D}"

S = "${WORKDIR}/git"

LIC_FILES_CHKSUM="file://${S}/LICENSE;md5=4fe869ee987a340198fb0d54c55c47f1"
LICENSE="GPLv3"

DEPENDS="pcompiler libwallaby"

do_install() {
  make install DESTDIR=${D}

  install -d ${D}/lib/systemd/system
  install -m 0644 ${WORKDIR}/botui.service ${D}/lib/systemd/system

  install -d ${D}/lib/systemd/system/graphical.target.wants/
  ln -sf ../botui.service ${D}/lib/systemd/system/graphical.target.wants/
}

FILES_${PN} += "/usr/lib/*.so /usr/local/lib/*.so /lib"
FILES_${PN}-dev = "/usr/include"
FILES_${PN}-dbg += "/usr/bin/botui/.debug"
