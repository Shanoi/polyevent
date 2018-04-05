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

changeDirectory('..')
executeCommand('mvn install -pl core -am')
