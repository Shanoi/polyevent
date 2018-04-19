import os
import shutil
import subprocess

###------------------------------------------------------------------------------
###                               Useful variables
###------------------------------------------------------------------------------

# Needed directories
mainDir = 'polyevent/'
mAccounting = mainDir + 'accounting/'
mDatabase = mainDir + 'database/'
mEvent = mainDir + 'event/'
mMessaging = mainDir + 'messaging/'
mOrganizer = mainDir + 'organizer/'
mResponsible = mainDir + 'responsible/'
mRooms = mainDir + 'rooms/'
directories = [mainDir, mAccounting, mDatabase, mEvent, mMessaging, mOrganizer, mResponsible, mRooms]

# Files
compileScript = 'CompileScript.py'

###------------------------------------------------------------------------------
###                               Script functions
###------------------------------------------------------------------------------

## Execute a command
## Param: [command:str] the command to execute
def executeCommand(command):
    print('Executing command \'' + command + '\' ...')
    subprocess.call(command, shell=True)
    print('\'' + command + '\' successfully executed!\n')

###------------------------------------------------------------------------------
###                             Useful functions
###------------------------------------------------------------------------------

## Change directory
## Param: [path:str] the path to the target directory
def changeDirectory(path):
    print('Entering directory ' + path + ' ...\n')
    os.chdir(path)

###------------------------------------------------------------------------------
###                             Script code
###------------------------------------------------------------------------------

changeDirectory(mDatabase)
executeCommand('python ' + compileScript)
changeDirectory('../..')
changeDirectory(mMessaging)
executeCommand('python ' + compileScript)
changeDirectory('../..')
changeDirectory(mOrganizer)
executeCommand('python ' + compileScript)
changeDirectory('../..')
changeDirectory(mResponsible)
executeCommand('python ' + compileScript)
changeDirectory('../..')
changeDirectory(mEvent)
executeCommand('python ' + compileScript)
changeDirectory('../..')
changeDirectory(mAccounting)
executeCommand('python ' + compileScript)
changeDirectory('../..')
changeDirectory(mRooms)
executeCommand('python ' + compileScript)