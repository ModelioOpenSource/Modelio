#!/bin/bash
#######################################################################################
#       Modelio launcher
#
# Parameters available:
#  -nl en/fr                                    : Define the user interface language
#  -clean                                       : Clean the cached data.
#  -workspace workspacePath                     : Open th modelio session which using the
#                                                 "dir_path" directory as workspace data.
#  -project  projectName                        : Open directly the project_name with modelio
#                                                 is used to create the new project.
#  -batch scriptFile.py                         : Execute a Jython script
#  -param key val                               : Set the key variable with the val value.
#                                                 This variable is available in jython script.
#
#######################################################################################

main()
{
	if [ -f "/etc/modelio-open-source4.0/modelio.config" ] ; then
		. "/etc/modelio-open-source4.0/modelio.config"
	else
		UBUNTU_MENUPROXY=0
		LIBOVERLAY_SCROLLBAR=0
		SWT_WEBKIT2=${SWT_WEBKIT2:-0}
	fi
	export UBUNTU_MENUPROXY LIBOVERLAY_SCROLLBAR SWT_WEBKIT2

	# Force the Adwaita light theme on Gnome desktop
	[ "${XDG_CURRENT_DESKTOP}" = "GNOME" ] && export GTK_THEME=Adwaita

	MODELIO_PATH="$(getModelioInstallPath "$0")"

	# Use the embedded jre
	[ -x "${MODELIO_PATH}/jre/bin/java" ] && export PATH="${MODELIO_PATH}/jre/bin":${PATH}

	# Run modelio
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
	m_exec="$(getRealFilePath "$0")"
	echo "$(dirname "${m_exec}")"
}

main "$@"
