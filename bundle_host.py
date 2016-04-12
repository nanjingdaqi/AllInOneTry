# /usr/bin/python

import BaseHTTPServer
import SocketServer
import SimpleHTTPServer


def main():
    httpd = SocketServer.TCPServer(('localhost', 3001), SimpleHTTPServer.SimpleHTTPRequestHandler)
    httpd.serve_forever()


if __name__ == '__main__':
    main()
