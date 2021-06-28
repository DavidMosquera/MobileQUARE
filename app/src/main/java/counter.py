import sys
import os
import pathlib

class MeassuredFile:
    def __init__(self, path):
        self.totalLines = 0;
        self.deletedLines = 0;
        self.editedLines = 0;
        self.addedLines = 0;
        self.automated = 0;
        self.executableLines = 0;
        if "model" in str(path):
            self.type = "MODEL"
        elif "controller" in str(path):
            self.type = "CONTROLLER"
        elif "view" in str(path):
            self.type = "VIEW"
        else:
            self.type = "OTHER"
        self.path = path;
    def calculateAutomated(self):
        self.executableLines = self.totalLines - self.deletedLines;
        self.automated = self.totalLines - self.deletedLines - self.editedLines - self.addedLines;
    def printMeassuredFile(self):
        string = str(self.type) + "," + str(self.path) + "," + str(self.totalLines) + "," +str(self.executableLines) +"," + str(self.deletedLines) + "," + str(self.editedLines) + "," + str(self.addedLines) + "," + str(self.automated);
        return string
    def readLine (self, line):
        splitLine = line.split("$")
        self.totalLines += 1;
        if(len(splitLine)>1):
            if splitLine[1][0] == "A":
                self.addedLines += 1;
            elif splitLine[1][0] == "D":
                self.deletedLines += 1;
            elif splitLine[1][0] == "E":
                self.editedLines += 1;

def exploreDirectory(path, listDir):
    for path in path.iterdir():
        if path.is_file():
            current_file = open (path, "r")
            newMeassuredFile = MeassuredFile(path)
            for line in current_file.readlines():
                newMeassuredFile.readLine(line)
            newMeassuredFile.calculateAutomated();
            print(newMeassuredFile.printMeassuredFile())
            current_file.close();
        elif path.is_dir():
            exploreDirectory(path, listDir)


if __name__ == "__main__":
    for path in pathlib.Path().iterdir():
        if path.is_dir():
            exploreDirectory(path, [])
            