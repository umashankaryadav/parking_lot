#!/usr/bin/env bash
# Add script to run program here.
# Complete bin/setup so that after it is
# run, bin/parking_lot can be used to launch
# it.

# This variable contains absolute path of this `parking_lot` script
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null && pwd )"

# Use DIR variable above to pinpoint your jar/executable/main class
# e.g.
# - if java
   java -cp $DIR/../target/parking_lot-1.0-SNAPSHOT.jar assignment.gojek.ParkingLotMain $1
# - if python
#   python3 $DIR/../main.py $1
# - if ruby
#   ruby $DIR/../main.rb $1
#
# Important: Above commands is just examples, please modify to suit your requirement as necessary