#!/bin/bash
#######################################################################################
#    Modelio launcher
#
# Parameters available:
#  -nl en/fr                                    : Define the user interface language
#  -clean                                       : Clean the cached data.
#  -workspace dir_path                          : Open th modelio session which using the
#                                                 "dir_path" directory as workspace data.
#  -project  proj_name                          : Open directly the project_name with modelio
#                                                 is used to create the new project.
#  -batch script_name.py                        : Execute a Jython script
#  -param key val                               : Set the key variable with the val value.
#                                                 This variable is available in jython script.
#
#######################################################################################

main()
{

	SWT_GTK3=0
	UBUNTU_MENUPROXY=0
	LIBOVERLAY_SCROLLBAR=0
	GDK_NATIVE_WINDOWS=1
	MODELIO_PATH="`getModelioInstallPath $0`"
	[ -z "${GTK2_RC_FILES}" ] && GTK2_RC_FILES="${MODELIO_PATH}/gtkrc-modelio"
	export SWT_GTK3 UBUNTU_MENUPROXY LIBOVERLAY_SCROLLBAR GDK_NATIVE_WINDOWS PATH GTK2_RC_FILES

	"${MODELIO_PATH}/modelio" "$@"
}

getRealFilePath()
{
	if [ -L "$1" ] ; then
		SLNK=$(\ls -l "$1"| sed -e "s|.* -> ||")
		if [ "${SLNK:0:1}" != "/" ] ; then
			FILE_PATH="$(dirname $1)/${SLNK}"
		else
			FILE_PATH="${SLNK}"
		fi
	else
		FILE_PATH="$1"
	fi
	FULL_PATH=$(cd -P -- "$(dirname -- "${FILE_PATH}")" && printf '%s\n' "$(pwd -P)/${FILE_PATH##*/}")
	echo "${FULL_PATH}"
}

getModelioInstallPath()
{
	m_exec=`getRealFilePath "$0"`
	echo $(dirname "${m_exec}")
}

main "$@"
