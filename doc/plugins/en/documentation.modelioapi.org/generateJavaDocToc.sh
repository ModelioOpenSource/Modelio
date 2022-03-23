#/bin/bash
###################################################################
# 
#    generateTocForJavaDoc.sh
# 
# generate the TOC file (eclipse documentation) for a JavaDoc documentation
# 
# 
# example : 
#  generateJavaDocToc.sh
#  generateJavaDocToc.sh '/home/fferran/mtools/vega/projects/documentation/mdaapi' "tocs/modelioapi.xml" "Modelio API"
# 
# 
#
###################################################################

DOC_ROOT="$1"
TOC_FILE="$2"  
TITLE="$3"     # Modelio API

#
# Default Value
#
if [ -z "${DOC_ROOT}" ] ; then
	DOC_ROOT="${PWD}"
fi
if [ -z "${TOC_FILE}" ] ; then
	TOC_FILE="tocs/modelioapi.xml"
fi
if [ -z "${TITLE}" ] ; then
	TITLE="Modelio API"
fi

if [ -n "${HOME}" ] ; then
	tmpfile=`mktemp  -q ${HOME}/tmp/updateTOC.XXXXXX`
else
	tmpfile=`mktemp  -q /tmp/updateTOC.XXXXXX`
fi

tocfile="${DOC_ROOT}/${TOC_FILE}"
htmldir="html"

if [ ! -d "${DOC_ROOT}" ] ; then
	echo "Missing parameters or doesn't find the root documentation."
	echo "[${tocfile}]"
	echo ""
	echo "example:"
	echo "$0 '/home/fferran/mtools/vega/projects/documentation/mdaapi' 'tocs/modelioapi.xml' 'Modelio API'"
	exit 1
fi


echo '<?xml version="1.0" encoding="UTF-8"?>' > "${tocfile}"
echo '<?NLS TYPE="org.eclipse.help.toc"?>' >> "${tocfile}"
echo "<toc topic=\"${htmldir}/overview-summary.html\" label=\"$TITLE\">" >> "${tocfile}"

cd "$DOC_ROOT"

find "${htmldir}" -mindepth 2 -name '*' | sort | while read entry ; do
	if [ -d "$entry" ] ; then
		isSVNdir=`echo $entry | grep "/.svn"`
		
		# On filtre les répertoires .svn
		if [ -z "$isSVNdir" ] ; then

			# on ne commence qu'à partir du namespace
			
			nbrfile=`find $entry -maxdepth 1 -name '*.html' | wc -l `
			isClass_use=`echo $entry | grep -E "/class-use$"`
			if [ "$nbrfile" != "0" -a -z "$isClass_use" ] ; then
				package_name=`echo $entry | sed -e "s|$htmldir\/||" | sed -e "s|\/|\.|g"` # on retire le nom html du début et on remplace les '/' par des '.'
				
				
				echo "Package: [$package_name] - [$entry]"
				echo "	<topic href=\"\" label=\"$package_name\" >"  >> "${tocfile}"
				find $entry -maxdepth 1 -name '*.html' | sort | while read html_file ; do
					class_name=`basename $html_file .html`
					
					
					if [ "$class_name" != "package-use" -a "$class_name" != "package-frame" -a "$class_name" != "package-tree" -a  "$class_name" != "package-summary" ] ; then
						echo "		<topic href=\"$html_file\" label=\"$class_name\" />" >> "${tocfile}"
						# echo "    ($html_file)"
					fi
				done
				echo "	</topic>" >> "${tocfile}"
			fi
			
		fi
	fi
done
echo "</toc>" >> "${tocfile}"
