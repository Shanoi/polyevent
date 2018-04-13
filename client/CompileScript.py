import os
import shutil
import subprocess

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
###                             Script code
###------------------------------------------------------------------------------

executeCommand('mvn clean install package')
